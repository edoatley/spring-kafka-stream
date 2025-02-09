package com.edoatley.sks;

import com.edoatley.sks.config.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaStreamApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApiApplication.class, args);
	}
}