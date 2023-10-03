package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DefaultMetadata;
import org.example.dto.KafkaEvent;
import org.example.dto.OrderDto;
import org.example.dto.OrderStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class PalmettoService {

    private final ConcurrentLinkedQueue<OrderDto> orderQueue = new ConcurrentLinkedQueue<>();
    private final KafkaMessageProducer kafkaMessageProducer;

    public void addOrderToQueue(OrderDto orderDto) {
        orderQueue.add(orderDto);
        var kafkaEvent = getKafkaEvent(orderDto, OrderStatus.IN_PROGRESS);
        kafkaMessageProducer.publish("notification", kafkaEvent);
    }

    @Scheduled(fixedRate = 30000) // 30 seconds
    public void processOrderQueue() {
        var orderDto = orderQueue.poll();
        if (orderDto != null) {
            log.info("Order received: {}", orderDto);
            var kafkaEvent = getKafkaEvent(orderDto, OrderStatus.READY);
            kafkaMessageProducer.publish("notification", kafkaEvent);
        } else {
            log.info("No orders in the queue.");
        }
    }

    private KafkaEvent getKafkaEvent(OrderDto orderDto, OrderStatus delivering) {
        return new KafkaEvent(new DefaultMetadata("v1", "palmetto-app", "order_processing", now()),
            Map.of("id", orderDto.getId(), "status", delivering));
    }

}
