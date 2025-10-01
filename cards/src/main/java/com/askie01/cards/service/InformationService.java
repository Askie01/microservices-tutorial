package com.askie01.cards.service;

import com.askie01.cards.dto.ContactInformationDTO;

public interface InformationService {
    String getBuildVersion();

    String getJavaVersion();

    String getMavenVersion();

    ContactInformationDTO getContactInformation();
}