package com.innolevel.controller;

import com.innolevel.entity.Quote;
import com.innolevel.entity.Value;
import com.innolevel.service.QuoteService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class QuoteControllerTest {

    @Mock
    private QuoteService service;

    @Autowired
    private QuoteController quoteController;

    @Before
    public void setup() {
        this.quoteController = new QuoteController(service);
    }

    @Test
    public void getQuote() {
        Mockito.when(service.buildQuoteRestTemplate()).thenReturn(createQuoteMock());

        final Quote response = quoteController.getQuote();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getType()).isEqualTo("type");
    }

    private Quote createQuoteMock() {
        Quote quote = new Quote();
        quote.setType("type");
        quote.setValue(new Value());
        return quote;
    }

}
