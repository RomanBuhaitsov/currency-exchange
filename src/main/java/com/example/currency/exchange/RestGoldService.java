package com.example.currency.exchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.logging.java.JavaLoggingSystem;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class RestGoldService {
    private final RestTemplate restTemplate;
    private final String URI = "http://api.nbp.pl/api/cenyzlota/last/14/?format=json";

    public RestGoldService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getGoldPrice() throws JSONException {
        String result = restTemplate.getForObject(URI, String.class);
        final JSONArray jsonArray = new JSONArray(result);
        ArrayList<Double> prices = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            prices.add(obj.getDouble("cena"));
        }

        Double sum = 0.;
        for (Double d:prices) {
            sum+=d;
        }

        return String.format("%.2f", sum/prices.size());
    }
}
