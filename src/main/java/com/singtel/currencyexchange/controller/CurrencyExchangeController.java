package com.singtel.currencyexchange.controller;

import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;
import com.singtel.currencyexchange.service.CurrencyExchangeService;
import com.singtel.currencyexchange.util.CurrencyExchangeUtil;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ok on 18/5/18.
 */
@Controller
public class CurrencyExchangeController {

    private static Logger log = LogManager.getLogger( CurrencyExchangeController.class );

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    /**
     * Given a date, get the exchange rate of all the currencies (wrt USD)
     * @param pDate, 2017-01-01
     * @return
     */
    @RequestMapping("/findAllBy1Date")
    public ResponseEntity<Map<String, Object>> findAllBy1Date(String pDate ) {

        // validation;
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            String date = CurrencyExchangeUtil.formateDate( pDate );

            if( CurrencyExchangeUtil.isEmpty( date ) ) {
                throw new Exception( "Date is not correct." );
            }

            List<CurrencyExchangeEntity> currencyExchangeEntities = currencyExchangeService.findAllBy1Date( date );

            log.info( "1: " + currencyExchangeEntities );

            map.put("success", new Boolean(true) );
            map.put("result", currencyExchangeEntities );

            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.OK);

        } catch ( Exception e ) {

            e.printStackTrace();
            map.put("success", new Boolean( false ) );
            map.put("msg", e.getMessage() );
            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    /**
     * Given a date and 2 currencies, find the exchange rate between them
     * @param pDate
     * @param currencyFrom
     * @param currencyTo
     * @return
     */
    @RequestMapping("/findEchangeRateByDate")
    public ResponseEntity<Map<String, Object>> findExchangeRateByDate(String pDate, String currencyFrom, String currencyTo ) {

        // validation;
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            String date = CurrencyExchangeUtil.formateDate( pDate );

            if( CurrencyExchangeUtil.isEmpty( date ) ) {
                throw new Exception( "Date is not correct." );
            }

            BigDecimal rate = currencyExchangeService.findExchangeRateByDate( date, currencyFrom, currencyTo );

            log.info( "rate: " + rate );

            map.put("success", new Boolean(true) );
            map.put("result", rate );

            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.OK);

        } catch ( Exception e ) {

            e.printStackTrace();
            map.put("success", new Boolean( false ) );
            map.put("msg", e.getMessage() );
            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    /**
     * Given a date range and a given currency, find the exchange rate
     * of that currency (wrt USD) for the entire date range
     * @param pDateBegin
     * @param pDateEnd
     * @param pCurrency
     * @return
     */
    @RequestMapping("/findAllByRangeDate")
    public ResponseEntity<Map<String, Object>> findAllByRangeDate( String pDateBegin, String pDateEnd, String pCurrency ) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {

            // validation;
            String dateBegin = CurrencyExchangeUtil.formateDate( pDateBegin );

            if( CurrencyExchangeUtil.isEmpty( dateBegin ) ) {
                throw new Exception( "Begin date is not correct." );
            }

            String dateEnd = CurrencyExchangeUtil.formateDate( pDateEnd );

            if( CurrencyExchangeUtil.isEmpty( dateEnd ) ) {
                throw new Exception( "End date is not correct." );
            }

            if( CurrencyExchangeUtil.isEmpty( pCurrency ) ) {
                throw new Exception( "Currency is not correct." );
            }

            List<CurrencyExchangeEntity> currencyExchangeEntities
                    = currencyExchangeService.findAllByRangeDate( dateBegin, dateEnd, pCurrency );

            log.info( "currencyExchangeEntities: " + currencyExchangeEntities );

            map.put("success", new Boolean(true) );
            map.put("result", currencyExchangeEntities );

            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.OK);

        } catch ( Exception e ) {

            e.printStackTrace();
            map.put("success", new Boolean( false ) );
            map.put("msg", e.getMessage() );
            return new ResponseEntity<Map<String, Object>>( map, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


}
