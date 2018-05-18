package com.singtel.currencyexchange;

import com.singtel.currencyexchange.cache.CurrencyExchangeCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class CurrencyExchangeApplication {


    private static Logger log = LogManager.getLogger( CurrencyExchangeApplication.class );

	public static void main(String[] args) {

		SpringApplication.run(CurrencyExchangeApplication.class, args);


	}
}
