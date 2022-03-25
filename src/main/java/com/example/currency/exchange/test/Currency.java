package com.example.currency.exchange.test;

public class Currency {
    public String getDateOfTrade() {
        return dateOfTrade;
    }

    public void setDateOfTrade(String dateOfTrade) {
        this.dateOfTrade = dateOfTrade;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    private String dateOfTrade;
    private String rate;

    public Currency(String date, String rate){
        this.dateOfTrade=date;
        this.rate=rate;
    }
}
