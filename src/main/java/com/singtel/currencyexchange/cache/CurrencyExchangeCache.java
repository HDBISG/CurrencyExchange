package com.singtel.currencyexchange.cache;

import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ok on 19/5/18.
 */

@Service("CurrencyExchangeCache")
public class CurrencyExchangeCache {

    private static Logger log = LogManager.getLogger( CurrencyExchangeCache.class );

    // Map<date, List<CurrencyExchangeEntity> >
    private static Map<String, List<CurrencyExchangeEntity> > dateCurrencyMap = new HashMap<>();

    // Map< Currency, List<CurrencyExchangeEntity> >
    private static Map<String, List<CurrencyExchangeEntity> > curencyDateMap = new HashMap<>();


    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void PostConstruct() {
        log.info( "PostConstruct()");
        this.readFile();
    }

    public void readFile(  ) {

        try {

            log.info( "resourceLoader= " + resourceLoader);

            Resource file = resourceLoader.getResource("classpath:application.properties");
            File applicationFile = file.getFile();

            File resources = applicationFile.getParentFile();
            log.info( "resources.exists()= " + resources.getAbsolutePath() );

            File currencyExchangeFolder = new File( resources.getAbsolutePath() + "/CurrencyExchangeFiles" );

            log.info( "currencyExchangeFolder.exists()= " + currencyExchangeFolder.exists() );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /********************/

    public static Map<String, List<CurrencyExchangeEntity>> getDateCurrencyMap() {
        return dateCurrencyMap;
    }

    public static void setDateCurrencyMap(Map<String, List<CurrencyExchangeEntity>> dateCurrencyMap) {
        CurrencyExchangeCache.dateCurrencyMap = dateCurrencyMap;
    }

    public static Map<String, List<CurrencyExchangeEntity>> getCurencyDateMap() {
        return curencyDateMap;
    }

    public static void setCurencyDateMap(Map<String, List<CurrencyExchangeEntity>> curencyDateMap) {
        CurrencyExchangeCache.curencyDateMap = curencyDateMap;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
