package com.askie01.configurationserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ConfigurationService {
    ResponseEntity<JsonNode> refreshConfigurations();
}
