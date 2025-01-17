package com.estevao.simple_camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:simpletimer")
                .routeId("test-simpletimer")
                .setBody(simple("Mensagem enviada às ${date:now:HH:mm:ss}"))
                .log(LoggingLevel.INFO, "${body}")
                .to("mock:result");

    }
}
