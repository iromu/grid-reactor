package es.blew.grid.reactor.controller;


import es.blew.grid.reactor.integration.Label;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class InfoController {

    @RequestMapping(value = "/info")
    public Mono<Label> starter() {
        return Mono.just(new Label("spring-boot-starter-web-reactive", "Spring Boot Web Reactive"));
    }
}