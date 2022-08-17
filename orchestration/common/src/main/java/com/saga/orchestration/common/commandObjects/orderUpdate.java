package com.saga.orchestration.common.commandObjects;

import com.saga.orchestration.common.dto.orderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderUpdate {
    private orderDto orderDto;
    private String orderStatus;
    private String inventoryStatus;
    private String paymentStatus;
}
