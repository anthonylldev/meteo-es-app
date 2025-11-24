package com.anthonylldev.meteoesapi.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aemet")
public record AemetProperties(
        String apiKey,
        String baseUrl
) {}

