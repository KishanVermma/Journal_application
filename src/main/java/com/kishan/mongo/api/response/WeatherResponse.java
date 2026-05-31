package com.kishan.mongo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current;
    @Getter
    @Setter
    public class Current {
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("feelslike_c")
        private double feelslikeC;
        @JsonProperty("feelslike_f")
        private double feelslikeF;
    }
    @Getter
    @Setter
    public class Condition {
        @JsonProperty("text")
        private String text;
        // Getters and Setters
    }
}

