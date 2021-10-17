package ru.simbial.exchangeratesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.simbial.exchangeratesservice.clients.OpenexchangeratesClient;
import ru.simbial.exchangeratesservice.models.CurrencyExchangeRatesData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static ru.simbial.exchangeratesservice.util.ValidationUtil.checkResponseEntityIsOkAndHasBody;

@Service
public class CurrencyChangesReactionServiceImpl implements CurrencyChangesReactionService {
    @Value("${openexchangerates.app.id}")
    private String app_id;

    private OpenexchangeratesClient openexchangeratesClient;
    private GiphyService giphyService;

    @Autowired
    public void setOpenexchangeratesClient(OpenexchangeratesClient openexchangeratesClient) {
        this.openexchangeratesClient = openexchangeratesClient;
    }

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    public byte[] getGifReactionToExchangeRateChanges(String currency_code) {
        ResponseEntity<Map<String, String>> currenciesMap = openexchangeratesClient.getAvailableCurrenciesMap(app_id);

        if (checkResponseEntityIsOkAndHasBody(currenciesMap)) {
            if (!currenciesMap.getBody().containsKey(currency_code)) {
                return null;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ResponseEntity<CurrencyExchangeRatesData> latestData =
                openexchangeratesClient
                        .getLatestRatesByCurrency(app_id, currency_code);
        ResponseEntity<CurrencyExchangeRatesData> yesterdayData =
                openexchangeratesClient
                        .getRatesByCurrencyByDate(LocalDate.now().minusDays(1).format(formatter), app_id, currency_code);

        byte[] gifBytes = null;

        if (checkResponseEntityIsOkAndHasBody(latestData) && checkResponseEntityIsOkAndHasBody(yesterdayData)) {
            gifBytes = giphyService.getReactionImageBytes(latestData.getBody(), yesterdayData.getBody(), currency_code);

        }
        return gifBytes;
    }
}
