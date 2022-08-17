package com.saga.orchestration.orchestration.service;

import com.saga.orchestration.common.dto.inventoryDto;
import com.saga.orchestration.common.dto.orderDto;
import com.saga.orchestration.orchestration.COPIEDmodel.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class orchestrationService {
    @Autowired
    StreamBridge streamBridge;

    public inventoryDto newInventoryEvent(orderDto orderDto){
        inventoryDto inventoryDto = new inventoryDto(orderDto.getOrderId(),
                orderDto.getProductId(), orderDto.getProductAmount());
        return inventoryDto;
    }
    public void cancelOrderEvent(orderDto orderDto){
        //command orderService to update order with orderRejected

    }
    public orderDto newPaymentEvent(orderDto orderDto){
        streamBridge.send("updateOrder-in-0", orderDto);
        return orderDto;
    }
    public void cancelInventoryEvent(orderDto orderDto){
        //command inventoryService to rollback changes
        cancelOrderEvent(orderDto);
    }
    public void finalOrderEvent(orderDto orderDto){
        //command orderService to update order with orderSuccessful
    }
}
