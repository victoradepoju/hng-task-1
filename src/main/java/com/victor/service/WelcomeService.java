package com.victor.service;

import com.victor.dtos.WelcomeResponse;
import com.victor.handler.NameNotFoundException;
import com.victor.model.GeoLocation;
import com.victor.model.Weather;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WelcomeService {

    @Value("${ip.geolocation.key}")
    private String ipGeolocationApiKey;

    @Value("${weather.api.key}")
    private String openWeatherMapApiKey;

    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Autowired
    public WelcomeService(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    public WelcomeResponse welcome(String visitor, String ipAddress) {

        if (visitor == null) {
            throw new NameNotFoundException("Oops! Seems you forgot to add your name using " +
                    "the 'visitor_name' query parameter");
        }

        if (visitor.isEmpty()) {
            throw new NameNotFoundException("Sorry, 'visitor_name' query parameter cannot be empty");
        }

        GeoLocation geoLocation = getGeo();

        String realIpAddress = ipAddress != null ? ipAddress : request.getRemoteAddr();
        double lat = geoLocation.latitude();
        double lon = geoLocation.longitude();

        Weather weather = getWeather(lat, lon, openWeatherMapApiKey);
        String city = weather.name();
        double temp = (double) weather.main().get("temp");
        String sanitizedName = visitor.replaceAll("\"", "").trim();

        return new WelcomeResponse(
                realIpAddress,
                city,
                "Hello, " + sanitizedName + "!, the temperature is " + temp + " degrees Celsius in " + city
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
}
