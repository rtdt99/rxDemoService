package com.rxproject.reactor.rxdemo.config;

import com.rxproject.reactor.rxdemo.handler.AppHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AppConfig {
    @Autowired
    AppHandler appHandler;

    @Bean
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions.route(GET("/greeting").and(accept(MediaType.APPLICATION_JSON)),appHandler::getGreeting);
    }

}
