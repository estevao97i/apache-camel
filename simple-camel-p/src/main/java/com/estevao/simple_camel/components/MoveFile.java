package com.estevao.simple_camel.components;

import com.estevao.simple_camel.exchanges.LogExchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveFile extends RouteBuilder {

    @Autowired
    private LogExchange logExchange;

    Logger logger = LoggerFactory.getLogger(MoveFile.class);

    @Override
    public void configure() throws Exception {

        from("file:src/files/input?fileName=inputFile.txt")
                .routeId("routeFileId")
                .process(logExchange)
                .to("file:src/files/output?fileName=outputFile.txt")
        .end();
    }

}
