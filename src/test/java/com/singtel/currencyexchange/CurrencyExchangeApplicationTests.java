package com.singtel.currencyexchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
	public void contextLoads() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        try {
            Thread.sleep( 1000 );
        } catch ( Exception e ) { e.printStackTrace(); }
	}

    @Test
    public void findAllBy1Date() throws Exception {

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/findAllBy1Date")
                        .param("pDate","2017-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn() ;

        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }


    @Test
    public void findEchangeRateByDate() throws Exception {

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/findEchangeRateByDate")
                        .param("pDate","2017-01-01")
                        .param("currencyFrom","EUR")
                        .param("currencyTo","SGD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn() ;

        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }

    @Test
    public void findAllByRangeDate() throws Exception {

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/findAllByRangeDate")
                        .param("pDateBegin","2017-01-01")
                        .param("pDateEnd","2018-01-01")
                        .param("pCurrency","SGD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn() ;

        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }

}
