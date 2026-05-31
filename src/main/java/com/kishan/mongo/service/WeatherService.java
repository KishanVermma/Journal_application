package com.kishan.mongo.service;

import com.kishan.mongo.api.response.WeatherResponse;
import com.kishan.mongo.cache.AppCache;
import com.kishan.mongo.constants.PlaceHolders;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Service
public class WeatherService {
    @Autowired
    private AppCache appCache;
    @Value("${weather.api.key}")
    private String apiKey;
    //private static final String API="https://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisConnectionFactory factory;

    @PostConstruct
    public void checkRedis() {

        RedisConnection conn = factory.getConnection();

        System.out.println("PING = " + conn.ping());

        Properties info = conn.serverCommands().info();

        System.out.println(info);
    }

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }
        else{
            String finalApi=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body=response.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }
    }
}
