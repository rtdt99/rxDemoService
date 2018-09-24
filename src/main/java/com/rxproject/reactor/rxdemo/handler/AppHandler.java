package com.rxproject.reactor.rxdemo.handler;

import com.rxproject.reactor.rxdemo.model.AppResponse;
import com.rxproject.reactor.rxdemo.model.Message;
import com.rxproject.reactor.rxdemo.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class AppHandler {
    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<Message> getMessage() {

        Mono<Message> messageMono = webClientBuilder
                                .build()
                                .get()
                                .uri("http://localhost:8080/hello")
                                .retrieve()
                                .bodyToMono(Message.class);
        return messageMono;
    }

    public Mono<Quote> getQuote(){
        Mono<Quote> quoteMono = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/quote")
                .retrieve()
                .bodyToMono(Quote.class);

        return quoteMono;
    }

    public Mono<ServerResponse> getGreeting(ServerRequest serverRequest){
        AppResponse appResponse = new AppResponse();

        return Mono.zip(getMessage().subscribeOn(Schedulers.parallel()),getQuote().subscribeOn(Schedulers.parallel()))
                .flatMap(tuple -> {
                    appResponse.setMessage(tuple.getT1());
                    appResponse.setQuote(tuple.getT2());
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(appResponse),AppResponse.class);
                });
    }

}
