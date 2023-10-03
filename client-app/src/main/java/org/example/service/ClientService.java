package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DefaultMetadata;
import org.example.dto.KafkaEvent;
import org.example.entity.Order;
import org.example.entity.OrderStatus;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
public record ClientService(KafkaMessageProducer kafkaMessageProducer,
                            OrderRepository orderRepository,
                            ObjectMapper objectMapper) {
    private static final String VERSION = "v1";
    private static final String EVENT_SOURCE = "client-app";
    private static final String EVENT_NAME = "new-order";

    public void publishEvent(Order order) {
        log.info("Publishing new order event!");
        var kafkaEvent = new KafkaEvent(
            new DefaultMetadata(VERSION, EVENT_SOURCE, EVENT_NAME, now()),
            order);
        kafkaMessageProducer.publish("order", kafkaEvent);
    }

    public Order getOrderInfo(Long id) {
        log.info("Retrieving order [{}] info", id);
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order: %s not exist!".formatted(id)));
    }

    public Order save(Order order) {
        log.info("Creating new order: {}", order);
        return orderRepository.save(order);
    }

    public Order updateStatusByOrderId(Long id, OrderStatus status) {
        log.info("Updating order [{}] status: {}", id, status);
        var order = getOrderInfo(id);
        order.setStatus(status);
        order.setUpdatedOn(now());
        return orderRepository.save(order);
    }
}
