package com.saga.orchestration.order.controller;

import com.saga.orchestration.order.model.order;
import com.saga.orchestration.order.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class orderController {
    @Autowired
    com.saga.orchestration.order.service.orderService orderService;
    @PostMapping("/createOrder")
    public order createOrder(@RequestBody order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/getAll")
    public List<order> getAllOrders(){
        return orderService.getAll();
    }
}
