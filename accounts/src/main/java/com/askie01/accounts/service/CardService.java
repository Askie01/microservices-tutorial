package com.askie01.accounts.service;

import com.askie01.accounts.dto.CardDTO;

public interface CardService {
    CardDTO getCard(String mobileNumber);
}
