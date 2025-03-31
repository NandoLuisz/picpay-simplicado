package com.luis.picpaysimplicadochallenger.service;

import com.luis.picpaysimplicadochallenger.domain.User;
import com.luis.picpaysimplicadochallenger.dto.transaction.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${notify.url}")
    private String notifyUrl;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();

        NotificationDto notificationRequest = new NotificationDto(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
                notifyUrl,
                notificationRequest,
                String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            throw new Exception("Serviço de notificação fora de do ar.");
        }

    }

}
