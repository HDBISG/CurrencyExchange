package com.singtel.currencyexchange.service.cache;

import com.singtel.currencyexchange.entity.CurrencyExchangeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ok on 19/5/18.
 */
public class CurrencyExchangeCacheAuxiliary {

    private static Logger log = LogManager.getLogger( CurrencyExchangeCacheAuxiliary.class );

    public void initCache( File currencyExchangeFolder ) {

        // Map<date, List<CurrencyExchangeEntity> >
        Map<String, List<CurrencyExchangeEntity> > dateCurrencyMap = new HashMap<>();

        // Map< Currency, List<CurrencyExchangeEntity> >
        Map<String, List<CurrencyExchangeEntity> > curencyDateMap = new HashMap<>();

        for( File yearFile : currencyExchangeFolder.listFiles() ) {

            for( File file: yearFile.listFiles() ) {
                log.info( "file:" + file.getName() );
                this.putToMap( dateCurrencyMap, curencyDateMap, file );
            }
        }

        CurrencyExchangeCacheImpl.setDateCurrencyMap( dateCurrencyMap );
        CurrencyExchangeCacheImpl.setCurencyDateMap( curencyDateMap );

    }

    private void putToMap(
            Map<String, List<CurrencyExchangeEntity> > dateCurrencyMap,
            Map<String, List<CurrencyExchangeEntity> > curencyDateMap,
            File file ) {

        List<CurrencyExchangeEntity> currencyExchangeEntityList = parseFile( file );

        for( CurrencyExchangeEntity cx: currencyExchangeEntityList ) {
            log.info( "currencyExchangeEntity:" + cx );

            // 1:
            List<CurrencyExchangeEntity> cxList = dateCurrencyMap.get( cx.getDate() );

            if( cxList == null ) {
                cxList = new ArrayList<>(10);
                dateCurrencyMap.put( cx.getDate(), cxList );
            }
            cxList.add( cx );

            // 2:

            List<CurrencyExchangeEntity> cxListByCurrency = curencyDateMap.get( cx.getCurrency() );

            if( cxListByCurrency == null ) {
                cxListByCurrency = new ArrayList<>(10);
                curencyDateMap.put( cx.getCurrency(), cxListByCurrency );
            }
            cxListByCurrency.add( cx );
        }
    }

    private List<CurrencyExchangeEntity> parseFile( File file ) {

        List<CurrencyExchangeEntity> currencyExchangeEntityList = new ArrayList<>(20);

        LineNumberReader lineNumberReader = null;

        try
        {
            String fileName = (file.getName().split("\\.") )[0];

            //Construct the LineNumberReader object
            lineNumberReader = new LineNumberReader(new FileReader( file ));

            //Read all lines now; Every read increase the line number by 1
            String line = null;
            while ((line = lineNumberReader.readLine()) != null)
            {
                //System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
                currencyExchangeEntityList.add( new CurrencyExchangeEntity( fileName, line ) );
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            //Close the LineNumberReader
            try {
                if (lineNumberReader != null){
                    lineNumberReader.close();
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return currencyExchangeEntityList;
    }

    public List<CurrencyExchangeEntity> clone( List<CurrencyExchangeEntity> ceList ) {
        if( ceList == null ) {
            return null;
        }

        List<CurrencyExchangeEntity> ceListClone = new ArrayList<>( ceList.size() );

        try {
            for( CurrencyExchangeEntity ce: ceList ) {
                ceListClone.add( (CurrencyExchangeEntity)ce.clone() );
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ceListClone;
    }

}
