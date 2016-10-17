package es.blew.grid.reactor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebReactiveConfiguration;

import java.util.List;


@Configuration
public class WebConfig extends WebReactiveConfiguration {
    @Override
    protected void configureMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Jackson2JsonEncoder jacksonEncoder = new Jackson2JsonEncoder();
        HttpMessageWriter corsWriter = new CorsHttpMessageWriter(jacksonEncoder);
        messageWriters.add(corsWriter);
        addDefaultHttpMessageWriters(messageWriters);
    }

}