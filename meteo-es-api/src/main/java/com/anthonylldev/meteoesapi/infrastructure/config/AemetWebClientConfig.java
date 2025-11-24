package com.anthonylldev.meteoesapi.infrastructure.config;

import com.anthonylldev.meteoesapi.infrastructure.config.properties.AemetProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Configuration
public class AemetWebClientConfig {

    private final AemetProperties aemetProperties;
    private final ObjectMapper objectMapper;

    public AemetWebClientConfig(AemetProperties aemetProperties, ObjectMapper objectMapper) {
        this.aemetProperties = aemetProperties;
        this.objectMapper = objectMapper;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(aemetProperties.baseUrl())
                .codecs(configurer -> {
                    configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
                    configurer.defaultCodecs().jackson2JsonDecoder(
                            new Jackson2JsonDecoder(
                                    objectMapper,
                                    MediaType.APPLICATION_JSON,
                                    new MediaType("text", "plain", Charset.forName("ISO-8859-15"))
                            )
                    );
                })
                .filter((request, next) -> {
                    URI original = request.url();

                    if (original.getQuery() != null && original.getQuery().contains("api_key=")) {
                        return next.exchange(request);
                    }

                    URI updated = UriComponentsBuilder
                            .fromUri(original)
                            .queryParam("api_key", aemetProperties.apiKey())
                            .build(true)
                            .toUri();

                    ClientRequest newRequest = ClientRequest.from(request)
                            .url(updated)
                            .build();

                    return next.exchange(newRequest);
                })
                .build();
    }
}