package com.singtel.currencyexchange.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ok on 18/5/18.
 */
public class CurrencyExchangeUtil {

    public static boolean isEmpty( String value ) {

        if (value == null || value.trim().length() == 0 ) {
            return true;
        }
        return false;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formateDate( String pdate ) {

        try {
            Date date = simpleDateFormat.parse( pdate );

            return simpleDateFormat.format( date );
        } catch ( Exception e ) {

        }

        return null;
    }

    public static void main(String[] argv ) {
        System.out.println( formateDate( "2017-1-1" ) );
    }

}
