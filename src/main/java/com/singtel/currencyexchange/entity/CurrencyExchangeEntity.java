package com.singtel.currencyexchange.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ok on 18/5/18.
 */
public class CurrencyExchangeEntity implements Serializable, Cloneable, Comparable<CurrencyExchangeEntity> {

    private String date ;

    private String currency;

    private BigDecimal rate;

    public CurrencyExchangeEntity() {

    }

    public CurrencyExchangeEntity(String date, String currency, BigDecimal rate ) {
        this.date = date;
        this.currency = currency;
        this.rate = rate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(CurrencyExchangeEntity o) {

        if( o == null ) {
            return  -1;
        }
        return this.getDate().compareTo( o.getDate() );
    }

    /******************************/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
