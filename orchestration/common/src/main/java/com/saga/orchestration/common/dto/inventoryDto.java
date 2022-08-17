package com.saga.orchestration.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class inventoryDto {
    private String orderId;
    private String productId;
    private Integer productAmount;
}
