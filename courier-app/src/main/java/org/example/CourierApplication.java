package org.example;

import org.example.config.KafkaConsumerConfig;
import org.example.config.KafkaProducerConfig;
import org.example.config.KafkaTopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({KafkaConsumerConfig.class, KafkaProducerConfig.class, KafkaTopicConfig.class})
public class CourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourierApplication.class, args);
    }
}
