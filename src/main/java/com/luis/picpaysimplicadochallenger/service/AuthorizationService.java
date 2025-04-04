package com.luis.picpaysimplicadochallenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.url}")
    private String authUrl;

    public boolean authorizeTransaction() {
        ResponseEntity<Map> response = restTemplate.getForEntity(authUrl, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && "success".equals(responseBody.get("status"))) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                return data != null && Boolean.TRUE.equals(data.get("authorization"));
            }
        }
        return false;
    }
}
