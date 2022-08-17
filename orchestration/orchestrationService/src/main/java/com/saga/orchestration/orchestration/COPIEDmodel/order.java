package com.saga.orchestration.orchestration.COPIEDmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class order {
    @Id
    private String orderId;
    private String userId;
    private Integer cost;
    private String productId;
    private Integer productAmount;
    private String orderStatus;
    private String inventoryStatus;
    private String paymentStatus;


}
