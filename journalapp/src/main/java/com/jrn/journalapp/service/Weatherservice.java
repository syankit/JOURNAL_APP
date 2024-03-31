package com.jrn.journalapp.service;

import com.jrn.journalapp.api.WeatherResponse;
import com.jrn.journalapp.cache.AppCache;
import com.jrn.journalapp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@Service
public class Weatherservice {
    @Value(("${weather.api.key}"))
    public String apikey;

//    public static final String apikey="QRF9AHL6M4FATZ6MPTX8686B4";
  // public static final String API="https://api.weatherstack.com/current? access_key=API_KEY & query = CITY";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;
//    public WeatherResponse getweather(String city){
//        String finalapi=API.replace("CITY",city).replace("API_KEY",apikey);
//        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalapi, HttpMethod.GET,null, WeatherResponse.class);
//        WeatherResponse body= response.getBody();
//        return body;
//    }
public WeatherResponse getWeather(String city) {
    String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apikey);
    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
    WeatherResponse body = response.getBody();
    return body;
}
}
