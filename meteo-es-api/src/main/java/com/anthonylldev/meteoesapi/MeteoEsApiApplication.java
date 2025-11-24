package com.anthonylldev.meteoesapi;

import com.anthonylldev.meteoesapi.infrastructure.config.properties.AemetProperties;
import com.anthonylldev.meteoesapi.infrastructure.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AemetProperties.class, CorsProperties.class})
@SpringBootApplication
public class MeteoEsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeteoEsApiApplication.class, args);
    }

}
