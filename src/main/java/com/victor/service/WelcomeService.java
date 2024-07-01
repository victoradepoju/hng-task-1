package com.victor.service;

import com.victor.dtos.WelcomeResponse;
import com.victor.model.GeoLocation;
import com.victor.model.Weather;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WelcomeService {

    @Value("${ip.geolocation.key}")
    private String ipGeolocationApiKey;

    @Value("${weather.api.key}")
    private String openWeatherMapApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WelcomeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WelcomeResponse welcome(String visitor) {
        String ipAddress = getIpFromGeo();
        double lat = getLatitudeFromGeo();
        double lon = getLongitudeFromGeo();

        Weather weather = getWeather(lat, lon, openWeatherMapApiKey);
        String city = weather.name();
        double temp = (double) weather.main().get("temp");


        return new WelcomeResponse(
                ipAddress,
                city,
                "Hello " + visitor + "!, the temperature is " + temp + " degrees Celsius in " + city
        );
    }

    public GeoLocation getGeo() {
        String locationUrl = String.format("https://api.ipgeolocation.io/ipgeo?apiKey=%s", ipGeolocationApiKey);

        return restTemplate.getForObject(locationUrl, GeoLocation.class);
    }

    public Weather getWeather(double lat, double lon, String apiKey) {
        String weatherUrl = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric", lat, lon, apiKey);

        return restTemplate.getForObject(weatherUrl, Weather.class);
    }

    public String getIpFromGeo() {
        return getGeo().ip();
    }

    public double getLatitudeFromGeo() {
        return getGeo().latitude();
    }
    public double getLongitudeFromGeo() {
        return getGeo().longitude();
    }
}
