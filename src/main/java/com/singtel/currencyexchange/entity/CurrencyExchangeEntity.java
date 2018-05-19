package com.singtel.currencyexchange.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by ok on 18/5/18.
 */
public class CurrencyExchangeEntity implements Serializable, Cloneable  {

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

    public CurrencyExchangeEntity( String date, String line ) {
        this.date = date;
        this.currency = line.substring( 2, 5);
        this.rate = new BigDecimal( line.substring( 16, 20) );
    }

    public static class AscDateComparaor implements Comparator< CurrencyExchangeEntity> {

        @Override
        public int compare(CurrencyExchangeEntity o1, CurrencyExchangeEntity o2) {
            return o1.getDate().compareTo( o2.getDate() );
        }
    }

    public static class AscCurrencyComparaor implements Comparator< CurrencyExchangeEntity> {

        @Override
        public int compare(CurrencyExchangeEntity o1, CurrencyExchangeEntity o2) {
            return o1.getCurrency().compareTo( o2.getCurrency() );
        }
    }

    @Override
    public String toString() {
        return "CurrencyExchangeEntity{" +
                "date='" + date + '\'' +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyExchangeEntity that = (CurrencyExchangeEntity) o;

        if (!date.equals(that.date)) return false;
        return currency.equals(that.currency);

    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
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
