package com.singtel.currencyexchange.service.cache;

import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ok on 19/5/18.
 */

@Service("CurrencyExchangeCache")
public class CurrencyExchangeCacheImpl implements CurrencyExchangeCache {

    private static Logger log = LogManager.getLogger( CurrencyExchangeCacheImpl.class );

    // Map<date, List<CurrencyExchangeEntity> >
    private static Map<String, List<CurrencyExchangeEntity> > dateCurrencyMap = null ;

    // Map< Currency, List<CurrencyExchangeEntity> >
    private static Map<String, List<CurrencyExchangeEntity> > curencyDateMap = null ;


    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public boolean isAvailbale(){
        if( dateCurrencyMap != null && dateCurrencyMap.size() > 0
                && curencyDateMap != null && curencyDateMap.size() > 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public List<CurrencyExchangeEntity> findAllBy1Date(String date ) {
        return new CurrencyExchangeCacheAuxiliary().clone( dateCurrencyMap.get( date ) ) ;
    }

    @Override
    public CurrencyExchangeEntity findExchangeRateByDate(String date, String currency ) {

        List<CurrencyExchangeEntity> ceList = dateCurrencyMap.get( date );

        if( ceList == null ) {
            return  null;
        }
        for( CurrencyExchangeEntity cx: ceList ) {

            if( currency.equalsIgnoreCase( cx.getCurrency() ) ) {

                CurrencyExchangeEntity ceClone = null;
                try {
                    ceClone = (CurrencyExchangeEntity)cx.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return ceClone;
            }
        }

        return null;
    }

    @Override
    public List<CurrencyExchangeEntity> findAllByRangeDate( String dateBegin, String dateEnd, String curency ) {

        List<CurrencyExchangeEntity> ceList = curencyDateMap.get( curency );

        int begin = ceList.indexOf( new CurrencyExchangeEntity(dateBegin, curency, null) );
        int end = ceList.indexOf( new CurrencyExchangeEntity(dateEnd, curency, null) );

        List<CurrencyExchangeEntity> rstList = ceList.subList( begin,end + 1 );

        return new CurrencyExchangeCacheAuxiliary().clone( rstList );
    }






    @PostConstruct
    public void PostConstruct() {
        log.info( "PostConstruct()");

    }

    @Override
    public void initCache() {

        new CurrencyExchangeCacheAuxiliary().initCache( getCurrencyExchangeFolder() );

        for( List<CurrencyExchangeEntity> cxList: dateCurrencyMap.values()) {
            Collections.sort( cxList, new CurrencyExchangeEntity.AscCurrencyComparaor() );
        }

        for( List<CurrencyExchangeEntity> cxList: curencyDateMap.values()) {
            Collections.sort( cxList, new CurrencyExchangeEntity.AscDateComparaor() );
        }
    }


    public File getCurrencyExchangeFolder() {

        try {

            log.info( "resourceLoader= " + resourceLoader);

            Resource file = resourceLoader.getResource("classpath:application.properties");
            File applicationFile = file.getFile();

            File resources = applicationFile.getParentFile();
            log.info( "resources.exists()= " + resources.getAbsolutePath() );

            File currencyExchangeFolder = new File( resources.getAbsolutePath() + "/CurrencyExchangeFiles" );

            log.info( "currencyExchangeFolder.exists()= " + currencyExchangeFolder.exists() );

            return currencyExchangeFolder;

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return null;
    }

    /********************/

    public static Map<String, List<CurrencyExchangeEntity>> getDateCurrencyMap() {
        return dateCurrencyMap;
    }

    public static void setDateCurrencyMap(Map<String, List<CurrencyExchangeEntity>> dateCurrencyMap) {
        CurrencyExchangeCacheImpl.dateCurrencyMap = dateCurrencyMap;
    }

    public static Map<String, List<CurrencyExchangeEntity>> getCurencyDateMap() {
        return curencyDateMap;
    }

    public static void setCurencyDateMap(Map<String, List<CurrencyExchangeEntity>> curencyDateMap) {
        CurrencyExchangeCacheImpl.curencyDateMap = curencyDateMap;
    }


}
