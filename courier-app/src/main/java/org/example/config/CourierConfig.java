package org.example.config;

//@Configuration
//public class CourierConfig {

//    @Bean
//    @Primary
//    public KafkaListenerContainerFactory<?> filterKafkaListenerContainerFactory(ConsumerFactory<Long, KafkaEvent> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<Long, KafkaEvent> factory =
//            new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setRecordFilterStrategy(
//            consumerRecord -> consumerRecord.value().toString().contains("READY"));
//        return factory;
//    }
//}
