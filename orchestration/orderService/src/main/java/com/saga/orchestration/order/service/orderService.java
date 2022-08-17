package com.saga.orchestration.order.service;

import com.saga.orchestration.order.model.order;
import com.saga.orchestration.order.repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {
    @Autowired
    com.saga.orchestration.order.repository.orderRepository orderRepository;

    @Autowired
    StreamBridge streamBridge;

    public order createOrder(order order){
        order toReturn = orderRepository.save(order);
        //notify orchestration service of order creation
        streamBridge.send("orchestration-in-0", order);

        return toReturn;
    }
    public List<order> getAll(){
        return orderRepository.findAll();
    }
}
