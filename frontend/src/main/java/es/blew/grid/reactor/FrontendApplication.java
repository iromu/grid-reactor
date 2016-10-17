package es.blew.grid.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FrontendApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
