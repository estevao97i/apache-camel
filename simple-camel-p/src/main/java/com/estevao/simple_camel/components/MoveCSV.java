package com.estevao.simple_camel.components;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MoveCSV extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(MoveCSV.class);

    @Override
    public void configure() throws Exception {

        from("file:src/csvs/input?fileName=input.csv")
                .routeId("moveCSV")
                .process(exchange -> {
                    String response = exchange.getIn().getBody(String.class);
                    logger.info("response ae csv: {}", response);
                })
                .to("file:src/csvs/output?fileName=output.csv");
    }
}
