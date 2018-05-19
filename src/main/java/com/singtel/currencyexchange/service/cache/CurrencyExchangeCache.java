package com.singtel.currencyexchange.service.cache;

import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ok on 19/5/18.
 */
public interface CurrencyExchangeCache {

    public void initCache();

    public boolean isAvailbale() ;

    public List<CurrencyExchangeEntity> findAllBy1Date(String date ) ;

    public CurrencyExchangeEntity findExchangeRateByDate(String date, String currency );

    public List<CurrencyExchangeEntity> findAllByRangeDate( String dateBegin, String dateEnd, String curency ) ;


}
