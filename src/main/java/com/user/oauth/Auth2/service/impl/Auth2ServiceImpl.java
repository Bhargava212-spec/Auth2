package com.user.oauth.Auth2.service.impl;

import com.user.oauth.Auth2.service.Auth2Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Auth2ServiceImpl implements Auth2Service {

    private static final Logger logger = LoggerFactory.getLogger(Auth2ServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.azure.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.azure.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.azure.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.registration.azure.scope}")
    private String scope;

    @Value("${spring.security.oauth2.client.registration.azure.authorization-grant-type}")
    private String grant;


    @Override
    @Transactional
    @CircuitBreaker(name = "tokenCircuitBreaker", fallbackMethod = "fallbackForToken")
    public String generateToken(UserDetails userDetails) {
        logger.info("START : Fetching the token for the user : {}", userDetails.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grant);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", userDetails.getUsername());
        body.add("password", userDetails.getPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        try {
            return restTemplate.exchange(tokenUri, HttpMethod.POST, request, String.class).getBody();
        } catch (RestClientException e) {
            logger.error("Error while Fetching the token for the user : {} with message : {}", userDetails.getUsername(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String fallbackForToken(UserDetails userDetails, Throwable throwable) {
        logger.error("Fallback method invoked for user : {} due to exception : {}", userDetails.getUsername(), throwable.getMessage());
        return "Server error - Try after some time";
    }
}
