package com.edoatley.sks.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate(Map<String, Object> producerConfigs) {
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(producerConfigs));
    }

    @Bean
    KafkaTemplate<String, String> kafkaTemplate(Map<String, Object> producerConfigs) throws ClassNotFoundException {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs));
    }

    @Bean
    ProducerFactory<String, String> producerFactory(Map<String, Object> producerConfigs) {
        return new DefaultKafkaProducerFactory<>(producerConfigs);
    }

    @Bean
    Map<String, Object> producerConfigs(KafkaProperties props) throws ClassNotFoundException {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServer(),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Class.forName(props.getKeySerializer()),
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Class.forName(props.getValueSerializer())
        );
    }
}
