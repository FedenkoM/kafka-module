package org.example.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.KafkaEvent;
import org.example.service.CourierService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class CourierKafkaListener {

    private final ObjectMapper objectMapper;
    private final CourierService courierService;

    @KafkaListener(id = "group_id_courier", topics = {"notification"}, containerFactory = "singleFactory")
    @SneakyThrows
    public void consume(KafkaEvent event) {
        log.info("=> consumed {}", writeValueAsString(event));
        var payload = (HashMap<String, Object>) event.getPayload();
        if ("READY".equals(payload.get("status").toString())) {
            courierService.deliverOrderToCustomer(Long.valueOf(payload.get("id").toString()));
        }
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
