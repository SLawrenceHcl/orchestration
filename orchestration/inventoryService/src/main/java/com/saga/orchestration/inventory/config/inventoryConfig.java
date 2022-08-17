package com.saga.orchestration.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class inventoryConfig {

    @Bean
    public Function<Flux<order>, Flux<order>> phase1(){
        return orderFlux -> orderFlux.flatMap(this::orderCreated);
    }

    private Mono<order> orderCreated(order order){

}
