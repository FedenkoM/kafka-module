package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DefaultMetadata;
import org.example.dto.KafkaEvent;
import org.example.entity.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
public record ClientService(KafkaMessageProducer kafkaMessageProducer, ObjectMapper objectMapper) {
    private static final String VERSION = "v1";
    private static final String EVENT_SOURCE = "client-app";
    private static final String EVENT_NAME = "new-order";

    public void publishEvent(Product product) {
        var kafkaEvent = new KafkaEvent(
            new DefaultMetadata(VERSION, EVENT_SOURCE, EVENT_NAME, now()),
            product);
        kafkaMessageProducer.publish("palmetto", kafkaEvent);
    }

    @KafkaListener(id = "group_id", topics = {"palmetto"}, containerFactory = "singleFactory")
    public void consume(KafkaEvent event) {
        log.info("=> consumed {}", writeValueAsString(event));
    }

    private String writeValueAsString(KafkaEvent dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
