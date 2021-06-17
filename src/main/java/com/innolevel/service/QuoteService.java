package com.innolevel.service;

import com.innolevel.entity.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

    private final String thirdPartyApiUrl = "https://quoters.apps.pcfone.io/api/random";

    @Autowired
    RestTemplate restTemplate;

    public Quote buildQuoteRestTemplate() {
        Quote quote;
        try {
            quote = restTemplate.getForObject(
                    thirdPartyApiUrl, Quote.class);
            log.info(quote.toString());
        } catch (HttpClientErrorException exception) {
            throw exception;
        }

        return quote;
    }

}
