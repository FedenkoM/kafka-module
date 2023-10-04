package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DefaultMetadata;
import org.example.dto.KafkaEvent;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierService {

    private final KafkaMessageProducer kafkaMessageProducer;

    @SneakyThrows
    public void deliverOrderToCustomer(Long id) {
        log.info("Delivering order to customer...");
        Thread.sleep(30000);
        kafkaMessageProducer.publish("notification", getKafkaEvent(id));
    }

    private KafkaEvent getKafkaEvent(Long id) {
        return new KafkaEvent(new DefaultMetadata("v1", "courier-app", "order_processing", now()),
            Map.of("id", id, "status", "DELIVERED"));
    }

}
