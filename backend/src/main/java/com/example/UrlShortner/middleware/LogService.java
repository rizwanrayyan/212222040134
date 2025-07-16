package com.example.UrlShortner.middleware;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogService {

    private final String BASE_URL = "http://20.244.56.144/evaluation-service/logs";
    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJyaXp3YW5yYXl5YW4wNzJAZ21haWwuY29tIiwiZXhwIjoxNzUyNjU4MzgyLCJpYXQiOjE3NTI2NTc0ODIsImlzcyI6IkFmZm9yZCBNZWRpY2FsIFRlY2hub2xvZ2llcyBQcml2YXRlIExpbWl0ZWQiLCJqdGkiOiJmOGIxMWI3NC02MzQ4LTRjY2UtYTk0MS03NDMzY2E4MDBhMTgiLCJsb2NhbGUiOiJlbi1JTiIsIm5hbWUiOiJyaXp3YW4gdCIsInN1YiI6IjZhN2NhMDU1LTEyMTEtNGMyZS05YTZjLWIxNmYyMTQ0OTMzNiJ9LCJlbWFpbCI6InJpendhbnJheXlhbjA3MkBnbWFpbC5jb20iLCJuYW1lIjoicml6d2FuIHQiLCJyb2xsTm8iOiIyMTIyMjIwNDAxMzQiLCJhY2Nlc3NDb2RlIjoicWd1Q2ZmIiwiY2xpZW50SUQiOiI2YTdjYTA1NS0xMjExLTRjMmUtOWE2Yy1iMTZmMjE0NDkzMzYiLCJjbGllbnRTZWNyZXQiOiJKYVVXaFZ3eVNtakZXc1NOIn0.U0R9g4I4FvRwn3BftHoIM0-wRGPFXpx71M_EOtDaKpc";

    public void log(String stack, String level, String pkg, String message) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> body = new HashMap<>();
        body.put("stack", stack);
        body.put("level", level);
        body.put("package", pkg);
        body.put("message", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(BEARER_TOKEN);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, request, String.class);
            System.out.println("Log sent: " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}
