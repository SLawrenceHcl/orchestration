package com.saga.orchestration.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderDto {
    private String orderId;
    private String userId;
    private Integer cost;
    private String productId;
    private Integer productAmount;
}
