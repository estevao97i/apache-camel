package com.estevao.simple_camel;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CamelSpringBootTest
@SpringBootTest
public class SimpleApacheCamelSpringTest {

    // Injeta um template para interagir com a rota
    @EndpointInject("direct:simpletimer")
    private ProducerTemplate producerTemplate;

    // Mock para verificar o resultado da rota
    @EndpointInject("mock:result")
    private MockEndpoint mockEndpoint;

    @Test
    public void testSimpleTimer() throws Exception {
        // Define o número esperado de mensagens
        mockEndpoint.expectedMessageCount(1);

        // Produz uma mensagem diretamente para o endpoint da rota
        producerTemplate.sendBody("direct:simpletimer", null);

        // Verifica se as mensagens foram recebidas corretamente
        mockEndpoint.assertIsSatisfied();

        // Verifica o corpo da mensagem recebida
        Exchange exchange = mockEndpoint.getExchanges().get(0);
        String body = exchange.getIn().getBody(String.class);

        // Valida que o corpo contém a mensagem esperada
        System.out.println("Mensagem recebida: " + body);
        assertEquals(true, body.contains("Mensagem enviada às"));
    }
}
