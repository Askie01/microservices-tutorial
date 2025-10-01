package com.askie01.configurationserver.controller;

import com.askie01.configurationserver.service.ConfigurationService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/configurations")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @PostMapping("/refresh")
    public ResponseEntity<JsonNode> refreshAllConfigurations() {
        return configurationService.refreshConfigurations();
    }
}
