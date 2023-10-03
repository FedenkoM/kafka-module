package org.example.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.KafkaEvent;
import org.example.entity.OrderStatus;
import org.example.service.ClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaListener {

    private final ObjectMapper objectMapper;
    private final ClientService clientService;

    @KafkaListener(id = "group_id", topics = {"notification"}, containerFactory = "singleFactory")
    @SneakyThrows
    public void consume(KafkaEvent event) {
        log.info("=> consumed {}", writeValueAsString(event));
        var payload = (HashMap<String, Object>) event.getPayload();
        clientService.updateStatusByOrderId(Long.valueOf(payload.get("id").toString()), OrderStatus.valueOf(payload.get("status").toString()));
    }

    private String writeValueAsString(Object dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }

}
