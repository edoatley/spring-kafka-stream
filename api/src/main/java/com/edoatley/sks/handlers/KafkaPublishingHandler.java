package com.edoatley.sks.handlers;

import com.edoatley.sks.model.StreamMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPublishingHandler {

    // TODO: Read this from config
    private static final String TOPIC = "testtopic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(StreamMessage streamMessage) {
        log.info("KafkaPublisher, publishing {}", streamMessage);
        String message = fromStreamMessage(streamMessage);
        log.info("KafkaPublisher, published {}", streamMessage);
//        kafkaTemplate.send(TOPIC, message);
    }

    public Flux<Void> publishFlux(Flux<StreamMessage> messages) {
        reactiveKafka.
    }

    private String fromStreamMessage(StreamMessage streamMessage) {
        return streamMessage.toString();
    }

}
