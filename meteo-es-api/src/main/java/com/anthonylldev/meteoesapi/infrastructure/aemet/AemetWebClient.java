package com.anthonylldev.meteoesapi.infrastructure.aemet;

import com.anthonylldev.meteoesapi.infrastructure.aemet.dto.AemetMetadataDto;
import com.anthonylldev.meteoesapi.infrastructure.aemet.exception.AemetRateLimitExceededException;
import com.anthonylldev.meteoesapi.municipality.infrastructure.adapter.out.aemet.dto.AemetMunicipalityDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Component
public class AemetWebClient {

    private final WebClient client;

    public AemetWebClient(WebClient client) {
        this.client = client;
    }

    public AemetMetadataDto getMunicipalityMetadata() {
        return client.get()
                .uri("/api/maestro/municipios")
                .retrieve()
                .onStatus(s -> s.value() == 429, r -> Mono.error(new AemetRateLimitExceededException()))
                .bodyToMono(AemetMetadataDto.class)
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(30))
                        .filter(AemetRateLimitExceededException.class::isInstance))
                .block();
    }

    public List<AemetMunicipalityDto> getMunicipalityList(String url) {
        return client.get()
                .uri(url)
                .retrieve()
                .onStatus(s -> s.value() == 429, r -> Mono.error(new AemetRateLimitExceededException()))
                .bodyToFlux(AemetMunicipalityDto.class)
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(30))
                        .filter(AemetRateLimitExceededException.class::isInstance))
                .collectList()
                .block();
    }
}
