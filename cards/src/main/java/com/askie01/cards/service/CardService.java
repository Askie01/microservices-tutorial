package com.askie01.cards.service;

import com.askie01.cards.dto.CardDTO;
import com.askie01.cards.entity.Card;

public interface CardService {
    Card createCard(String mobileNumber);

    Card getCard(String mobileNumber);

    Card updateCard(CardDTO cardDTO);

    Card deleteCard(String mobileNumber);
}
