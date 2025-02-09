package com.edoatley.sks.handlers;

import com.edoatley.sks.config.KafkaProperties;
import com.edoatley.sks.model.StreamMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPublishingHandler {

    private final KafkaProperties kafkaProperties;
    private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducer;

    public void publish(StreamMessage streamMessage) {
        log.info("KafkaPublisher, publishing {}", streamMessage);
        String message = fromStreamMessage(streamMessage);
        log.info("KafkaPublisher, published {}", message);
//        kafkaTemplate.send(TOPIC, message);
    }

    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<Void> handleStream(@RequestBody Flux<StreamMessage> messages) {
        return messages
                .map(this::fromStreamMessage)
                .flatMap(m -> reactiveKafkaProducer.send(kafkaProperties.getTopicName(), m))
                .then();
//                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", message, senderResult.recordMetadata().offset()))
//                .subscribe();
    }

    private String fromStreamMessage(StreamMessage streamMessage) {
        return streamMessage.toString();
    }

}
