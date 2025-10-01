package com.askie01.configurationserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class SimpleConfigurationService implements ConfigurationService {

    private final RestClient restClient;

    @Override
    public ResponseEntity<JsonNode> refreshConfigurations() {
        return restClient.post()
                .uri("/actuator/busrefresh")
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body("{}")
                .retrieve()
                .toEntity(JsonNode.class);
    }
}
