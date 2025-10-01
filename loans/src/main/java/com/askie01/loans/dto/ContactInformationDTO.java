package com.askie01.loans.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
@Component
@ConfigurationProperties(prefix = "loans")
public class ContactInformationDTO {
    private String message;
    private Map<String, String> contactDetails;
    private List<Integer> onCallSupport;
}
