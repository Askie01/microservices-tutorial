package com.askie01.accounts.service;

import com.askie01.accounts.dto.ContactInformationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
@RequiredArgsConstructor
public class CoreInformationService implements InformationService {

    @Value("${build.version}")
    private String buildVersion;
    private final Environment environment;
    private final ContactInformationDTO contactInformation;

    @Override
    public String getBuildVersion() {
        return buildVersion;
    }

    @Override
    public String getJavaVersion() {
        return environment.getProperty("JAVA_HOME");
    }

    @Override
    public String getMavenVersion() {
        return environment.getProperty("MAVEN_HOME");
    }

    @Override
    public ContactInformationDTO getContactInformation() {
        return contactInformation;
    }
}
