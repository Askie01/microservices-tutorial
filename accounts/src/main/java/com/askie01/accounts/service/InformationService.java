package com.askie01.accounts.service;

import com.askie01.accounts.dto.ContactInformationDTO;

public interface InformationService {
    String getBuildVersion();

    String getJavaVersion();

    String getMavenVersion();

    ContactInformationDTO getContactInformation();
}
