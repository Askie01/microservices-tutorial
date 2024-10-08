package com.askie01.cards.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "cards")
public class CardContactInfoDTO {
    private String message;
    private Map<String, String> contactDetails;
    private List<Integer> onCallSupport;
}
