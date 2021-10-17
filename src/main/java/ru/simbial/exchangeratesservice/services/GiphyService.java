package ru.simbial.exchangeratesservice.services;

import org.springframework.stereotype.Service;
import ru.simbial.exchangeratesservice.models.CurrencyExchangeRatesData;

@Service
public interface GiphyService {

    byte[] getReactionImageBytes(CurrencyExchangeRatesData latestData,
                                 CurrencyExchangeRatesData yesterdayData,
                                 String currency_code);
}
