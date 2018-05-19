package com.singtel.currencyexchange.service;


import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;

import com.singtel.currencyexchange.service.cache.CurrencyExchangeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CurrencyExchangeService")

public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    @Autowired
    CurrencyExchangeCache currencyExchangeCache;

    public List<CurrencyExchangeEntity> findAllBy1Date(String date ) {
        return null;
    }

    public BigDecimal findExchangeRateByDate(String date, String currencyFrom, String currencyTo ) {
        return null;
    }

    public List<CurrencyExchangeEntity> findAllByRangeDate( String dateBegin, String dateEnd, String curency ) {
        return null;
    }
     
}

