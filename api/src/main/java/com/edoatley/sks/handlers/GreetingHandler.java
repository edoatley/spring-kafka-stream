package com.edoatley.sks.handlers;

import com.edoatley.sks.model.GreetingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        log.info("Received a request in GreetingHandler.hello()");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new GreetingMessage("Hello, Spring!")));
    }
}
