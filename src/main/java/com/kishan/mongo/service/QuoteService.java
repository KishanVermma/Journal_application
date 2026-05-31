package com.kishan.mongo.service;

import com.kishan.mongo.api.response.QuoteResponse;
import com.kishan.mongo.model.User;
import com.kishan.mongo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {
    private static final String Api="https://api.api-ninjas.com/v2/randomquotes?categories=success,wisdom";
    private static String Api1="";
    @Autowired
    private RestTemplate restTemplate;
    public QuoteResponse getQuote(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", "fjBw3YFR1u2so80fhlkGrv1LWulvBn6rIj2Wwajf");
        String body="";
        HttpEntity<String> httpEntity=new HttpEntity<>(body,headers);
        ResponseEntity<List<QuoteResponse>> response=restTemplate.exchange(Api, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<QuoteResponse>>(){});
        return response.getBody().get(0);
    }
    public User getUser(){
        String body="{\n" +
                "  \"username\":\"nisha\",\n" +
                "  \"password\":\"5000\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity=new HttpEntity<>(body,headers);
        Api1="http://localhost:8081/public/create-user";
        ResponseEntity<?> res=restTemplate.exchange(Api1,HttpMethod.POST,httpEntity, User.class);
        return (User) res.getBody();
    }
}
 