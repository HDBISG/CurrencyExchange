package com.singtel.currencyexchange.service;


import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyExchangeService {

    public List<CurrencyExchangeEntity> findAllBy1Date(String date ) ;

    public BigDecimal findExchangeRateByDate(String date, String currencyFrom, String currencyTo );

    public List<CurrencyExchangeEntity> findAllByRangeDate( String dateBegin, String dateEnd, String curency ) ;

}