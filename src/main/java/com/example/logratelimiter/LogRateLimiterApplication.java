package com.example.logratelimiter;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class LogRateLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogRateLimiterApplication.class, args);
    }


    final Logger logger = RateLimiterLoggerFactory.getLogger(LogRateLimiterApplication.class);

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
            .GET("/", req -> {
                for (int i = 0; i < 100; i++) {
                    logger.error("error {}", i);
                }
                return ServerResponse.ok().syncBody("Hello World!");
            })
            .build();
    }
}
