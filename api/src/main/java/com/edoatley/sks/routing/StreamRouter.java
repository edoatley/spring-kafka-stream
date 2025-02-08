package com.edoatley.sks.routing;

import com.edoatley.sks.model.StreamMessage;
import com.edoatley.sks.handlers.KafkaPublishingHandler;
import com.edoatley.sks.handlers.GreetingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyExtractors.toFlux;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class StreamRouter {

  private final KafkaPublishingHandler kafkaPublisher;
  private final GreetingHandler greetingHandler;

  @Bean
  public RouterFunction<ServerResponse> route() {
    return RouterFunctions.route(
            GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello)
        .and(
            RouterFunctions.route(
                POST("/publish").and(accept(MediaType.APPLICATION_NDJSON)),
                req ->
                    req.body(toFlux(StreamMessage.class))
                        .doOnNext(kafkaPublisher::publish)
                        .then(ok().build())));
  }
}
