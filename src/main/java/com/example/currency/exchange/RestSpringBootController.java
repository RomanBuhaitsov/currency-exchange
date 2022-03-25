package com.example.currency.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestSpringBootController {
    private final RestGoldService goldService;
    private final RestCurrencyService currencyService;

    @Autowired
    public RestSpringBootController(RestGoldService goldService, RestCurrencyService currencyService){
        this.goldService=goldService;
        this.currencyService=currencyService;
    }


    @GetMapping("/api/gold-price/average")
    public ResponseEntity<?> getGoldPrice() {
        try {
            String result = this.goldService.getGoldPrice();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/exchange-rates/{currencyCode}")
    public ResponseEntity<?> getCurrencyRate(@PathVariable String currencyCode) {
        try {
            String result = this.currencyService.getExchangeRate(currencyCode);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
