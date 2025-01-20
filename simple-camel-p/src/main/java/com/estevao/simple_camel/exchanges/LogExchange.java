package com.estevao.simple_camel.exchanges;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogExchange implements Processor {

    Logger logger = LoggerFactory.getLogger(LogExchange.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            String response = exchange.getIn().getBody(String.class);
            logger.info("Exchange response: {}", response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
