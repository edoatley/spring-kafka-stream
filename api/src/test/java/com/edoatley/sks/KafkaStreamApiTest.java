package com.edoatley.sks;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.edoatley.sks.model.GreetingMessage;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("IT")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KafkaStreamApiTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testHello() {
		webTestClient
				// Create a GET request to test an endpoint
				.get().uri("/hello")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				// and use the dedicated DSL to test assertions against the response
				.expectStatus().isOk()
				.expectBody(GreetingMessage.class).value(greeting -> {
					assertThat(greeting.getMessage()).isEqualTo("Hello, Spring!");
				});
	}

//	@Test
//	void shouldPublishAStream(){
//		Flux<StreamMessage> messageStream = Flux.just(
//				new StreamMessage("a", "b"),
//				new StreamMessage("c", "d"),
//				new StreamMessage("e", "f")
//		);
//		webTestClient
//				.post().uri("/publish")
//				.contentType(MediaType.APPLICATION_NDJSON)
//				.body(BodyInserters.fromValue(messageStream))
//				.exchange()
//				.expectStatus().isOk()
//				.expectBody().returnResult().toString();
//
//	}
}