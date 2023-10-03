package org.example.config;

import org.example.dto.KafkaEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class CourierConfig {

    @Bean
    @Primary
    public KafkaListenerContainerFactory<?> filterKafkaListenerContainerFactory(ConsumerFactory<Long, KafkaEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Long, KafkaEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setRecordFilterStrategy(
            consumerRecord -> consumerRecord.value().toString().contains("READY"));
        return factory;
    }
}
