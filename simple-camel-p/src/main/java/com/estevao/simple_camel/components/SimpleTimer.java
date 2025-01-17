package com.estevao.simple_camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:simpletimer?period=5000")
                .setBody(simple("Mensagem enviada às ${date:now:HH:mm:ss}"))
                .log(LoggingLevel.INFO, "${body}");

    }
}
