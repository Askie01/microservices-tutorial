package com.askie01.loans.service;

import com.askie01.loans.dto.ContactInformationDTO;

public interface InformationService {
    String getBuildVersion();

    String getJavaVersion();

    String getMavenVersion();

    ContactInformationDTO getContactInformation();
}
