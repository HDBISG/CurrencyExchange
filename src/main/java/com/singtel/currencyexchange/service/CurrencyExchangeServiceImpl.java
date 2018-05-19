package com.singtel.currencyexchange.service;


import com.singtel.currencyexchange.controller.CurrencyExchangeController;
import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;

import com.singtel.currencyexchange.service.cache.CurrencyExchangeCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CurrencyExchangeService")

public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private static Logger log = LogManager.getLogger( CurrencyExchangeController.class );

    @Autowired
    CurrencyExchangeCache currencyExchangeCache;

    public List<CurrencyExchangeEntity> findAllBy1Date(String date ) {

        if( currencyExchangeCache.isAvailbale() ) {
            return currencyExchangeCache.findAllBy1Date( date );
        } else {
            // read file directly.
        }
        return null;
    }

    public BigDecimal findExchangeRateByDate(String date, String currencyFrom, String currencyTo ) {

        if( currencyExchangeCache.isAvailbale() ) {

            CurrencyExchangeEntity fEntity =
                    currencyExchangeCache.findExchangeRateByDate( date, currencyFrom );

            CurrencyExchangeEntity eEntity =
                    currencyExchangeCache.findExchangeRateByDate( date, currencyTo );

            log.info( "fEntity = " + fEntity + "\n eEntity= " + eEntity );
            return fEntity.getRate().divide( eEntity.getRate(), 2, BigDecimal.ROUND_HALF_UP);

        } else {
            // read file directly.
        }
        return null;
    }

    public List<CurrencyExchangeEntity> findAllByRangeDate( String dateBegin, String dateEnd, String curency ) {

        if( currencyExchangeCache.isAvailbale() ) {
            return currencyExchangeCache.findAllByRangeDate( dateBegin, dateEnd, curency );
        } else {
            // read file directly.
        }
        return null;
    }
     
}

