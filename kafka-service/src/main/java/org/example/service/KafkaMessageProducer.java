package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<Object, KafkaEvent> kafkaTemplate;

    public void publish(String topic, String routingKey, KafkaEvent payload) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", topic, routingKey, payload);
        kafkaTemplate.send(topic, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", topic, routingKey, payload);
    }

    public void publish(String topic, KafkaEvent payload) {
        log.info("Publishing to {}. Payload: {}", topic, payload);
        kafkaTemplate.send(topic, payload);
        log.info("Published to {}. Payload: {}", topic, payload);
    }

}