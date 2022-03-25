package com.example.currency.exchange.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RestCurrencyService {
    private final RestTemplate restTemplate;
    private final String URI = "http://api.nbp.pl/api/exchangerates/rates/a/CURRENCY/last/5/?format=json";

    public RestCurrencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getExchangeRate(String currencyCode) throws JSONException {
        String result = restTemplate.getForObject(URI.replace("CURRENCY", currencyCode), String.class);
        final JSONObject jsonObject = new JSONObject(result);
        final JSONArray jsonArray = jsonObject.getJSONArray("rates");
        ArrayList<Currency> rates = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String date = obj.getString("effectiveDate");
            String rate = obj.getString("mid");
            rates.add(new Currency(date, rate));
        }

        StringBuilder output = new StringBuilder();
        output.append(jsonObject.getString("code")).append("\n");
        for (Currency c: rates) {
            output.append(c.getDateOfTrade()).append(": ").append(c.getRate()).append("\n");
        }

        return output.toString();
    }
}
