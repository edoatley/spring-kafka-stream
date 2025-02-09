package com.edoatley.sks.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "ksa.kafka")
public class KafkaProperties {
    private String bootstrapServer;
    private String topicName;
    private String keySerializer;
    private String valueSerializer;
}
