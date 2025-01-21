package com.estevao.simple_camel.components;

import com.estevao.simple_camel.models.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MoveCSV extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(MoveCSV.class);
    BeanIODataFormat dataFormat = new BeanIODataFormat("InboungMessageIOCamel.xml", "inputMessageStream");

    @Override
    public void configure() throws Exception {

        from("file:src/csvs/input?fileName=input.csv")
                .routeId("moveCSV")
//                .split(body().tokenize("\n", 1, true))
                .unmarshal(dataFormat)
                .process(exchange -> {
                    Person response = exchange.getIn().getBody(Person.class);
                    logger.info("response ae csv: {}", response.toString());
                    exchange.getIn().setBody(response.toString());
                })
                .to("file:src/csvs/output?fileName=output.csv")
            .end();
    }
}
