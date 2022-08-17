package com.saga.orchestration.order.repository;

import com.saga.orchestration.order.model.order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface orderRepository extends MongoRepository<order, String> {
}
