package ru.simbial.exchangeratesservice.services;

import org.springframework.stereotype.Service;

@Service
public interface CurrencyChangesReactionService {

    byte[] getGifReactionToExchangeRateChanges(String currency_code);
}
