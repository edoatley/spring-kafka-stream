package com.edoatley.sks.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

import java.util.Map;

public class KafkaConfiguration {

    @Value("kafkastream.app.bootstrap-server")
    String bootstrapServer;

    @Bean
    ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate() {
        SenderOptions<String, Object> senderOptions = null;
        return new ReactiveKafkaProducerTemplate<>(senderOptions);
    }

    @Bean
    KafkaTemplate<String, String> kafkaTemplate() {
        Map<String, Object> configProps = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
    }

    @Bean
    ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        return Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );
    }
}
