package org.example.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.KafkaEvent;
import org.example.dto.OrderDto;
import org.example.service.PalmettoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PalmettoKafkaListener {

    private final ObjectMapper objectMapper;
    private final PalmettoService palmettoService;

    @KafkaListener(id = "group_id", topics = {"order"}, containerFactory = "singleFactory")
    @SneakyThrows
    public void consume(KafkaEvent event) {
        log.info("=> consumed {}", writeValueAsString(event));
        var payload = event.getPayload();
        var orderDto = objectMapper.readValue(writeValueAsString(payload), OrderDto.class);
        palmettoService.addOrderToQueue(orderDto);
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
