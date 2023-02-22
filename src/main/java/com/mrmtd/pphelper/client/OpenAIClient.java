package com.mrmtd.pphelper.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenAIClient {

    private final String API_URL = "https://api.openai.com/v1/completions";
    private final String AUTH_TOKEN = "Bearer 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";
    private final String API_KEY = "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";
    private RestTemplate restTemplate;

    public OpenAIClient() {
        restTemplate = new RestTemplate();
    }

    public String sendCompletionRequest(String prompt, int maxTokens, double temperature) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", AUTH_TOKEN);
        headers.set("OPENAI_API_KEY", API_KEY);

        String requestBody = 
        		String.format(""
				+ "{ \"model\": \"text-davinci-003\","
				+ " \"prompt\": \"%s\","
				+ " \"max_tokens\": %d,"
				+ " \"temperature\": %.1f }",
        		prompt,
        		maxTokens,
        		temperature);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error sending completion request: " + response.getStatusCode());
        }
    }

}