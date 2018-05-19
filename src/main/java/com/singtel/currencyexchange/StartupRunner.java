package com.singtel.currencyexchange;

import com.singtel.currencyexchange.service.cache.CurrencyExchangeCache;
import com.singtel.currencyexchange.service.cache.CurrencyExchangeCacheImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by ok on 19/5/18.
 */
@Component
public class StartupRunner implements CommandLineRunner {

    private static Logger log = LogManager.getLogger( StartupRunner.class );

    @Autowired
    CurrencyExchangeCache currencyExchangeCache;

    @Override
    public void run(String... args) throws Exception {

        log.info( " startup StartupRunner" );

        this.initCache();
    }

    private void initCache() {

        ExecutorService executor = Executors.newCachedThreadPool();


        Callable task = new  Callable<Integer>(){


            @Override
            public Integer call() throws Exception {

                currencyExchangeCache.initCache();

                return 0;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>( task );
        executor.submit(futureTask);
        executor.shutdown();
    }
}
