package com.saga.orchestration.orchestration.config;

import com.saga.orchestration.common.commandObjects.orderUpdate;
import com.saga.orchestration.common.dto.inventoryDto;
import com.saga.orchestration.common.dto.orderDto;
import com.saga.orchestration.common.dto.paymentDto;
import com.saga.orchestration.orchestration.COPIEDmodel.order;
import com.saga.orchestration.orchestration.service.orchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.saga.orchestration.common.*;

import java.util.function.Function;

@Configuration
public class orchestrationConfig {
    @Autowired
    com.saga.orchestration.orchestration.service.orchestrationService orchestrationService;
    @Autowired
    StreamBridge streamBridge;

    @Bean
    public Function<Flux<orderDto>, Flux<inventoryDto>> phase1(){
        return orderFlux -> orderFlux.flatMap(this::orderCreated);
    }

    private Mono<inventoryDto> orderCreated(orderDto orderDto){
//        if(order.getOrderStatus().equals("orderCreated")){
//            System.out.println("Sending inventoryEvent");
//            return Mono.fromSupplier(() -> orchestrationService.newInventoryEvent(order));
//        }else{
//            return Mono.fromRunnable(() -> orchestrationService.cancelInventoryEvent(order));
//        }

            //command orderService to update order objects orderStatus
//            order.setOrderStatus("orderCreated");
            orderUpdate order = new orderUpdate(orderDto, "orderCreated",null, null);
            streamBridge.send("updateOrder-in-0", order);

            return Mono.fromSupplier(() -> orchestrationService.newInventoryEvent(orderDto));

    }

    @Bean
    public Function<Flux<inventoryDto>, Flux<paymentDto>> phase2(){
        return orderFlux -> orderFlux.flatMap(this::inStock);
    }

    private Mono<paymentDto> inStock(inventoryDto inventoryDto){
        //query orchestration db for order

        if(order.getInventoryStatus().equals("inventoryApproved")){
            //command orderService to update order objects inventoryStatus
            System.out.println("Sending paymentEvent");
            return Mono.fromSupplier(() -> orchestrationService.newPaymentEvent(orderDto));
        }else{
            return Mono.fromRunnable(() -> orchestrationService.cancelOrderEvent(orderDto));
        }
    }
    private Mono<order> sufficientFunds(order order){
        //command orderService to update order object with paymentApproved
        if(order.getPaymentStatus().equals("fundsReserved")){
            System.out.println("Sending final updates");
            return Mono.fromSupplier(() -> orchestrationService.finalOrderEvent(order));
        }else{
            return Mono.fromRunnable(() -> orchestrationService.cancelInventoryEvent(order));
        }
    }
}
