package ru.simbial.exchangeratesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.simbial.exchangeratesservice.models.CurrencyExchangeRatesData;
import ru.simbial.exchangeratesservice.clients.GiphyClient;

import static ru.simbial.exchangeratesservice.util.GiphyUtil.*;
import static ru.simbial.exchangeratesservice.services.GiphyServiceGif.Tag.BROKE;
import static ru.simbial.exchangeratesservice.services.GiphyServiceGif.Tag.RICH;

@Service
public class GiphyServiceGif implements GiphyService{

    @Value("${giphy.gif.rating}")
    private String rating;

    @Value("${giphy.api.id}")
    private String app_id;

    private GiphyClient giphyClient;

    @Autowired
    public void setGiphyClient(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    public byte[] getReactionImageBytes(CurrencyExchangeRatesData latestData,
                                        CurrencyExchangeRatesData yesterdayData,
                                        String currency_code) {
        byte[] result = null;
        String gifUrl = getReactionGifUrl(latestData, yesterdayData, currency_code);
        if (gifUrl!= null) {
           String webpUrl = getGiphyWebpFormatFromGifUrl(gifUrl);
            result = getUrlImageBytes(webpUrl);
        }
        return result;
    }

    public String getReactionGifUrl(CurrencyExchangeRatesData latestData,
                                    CurrencyExchangeRatesData yesterdayData,
                                    String currency_code) {
        String result = null;
        Double latestRates = latestData.getRates().getOrDefault(currency_code, null);
        Double yesterdayRates = yesterdayData.getRates().getOrDefault(currency_code, null);

        if (latestRates != null && yesterdayRates != null) {
            String tag = latestRates.compareTo(yesterdayRates) > 0 ? RICH.label : BROKE.label;
            ResponseEntity<String> json =
                    giphyClient
                            .getRandomGifByTag(app_id, tag, rating);
            if (json.hasBody()) {
                result = getGifUrlFromGiphyJson(json.getBody());
            }
        }
        return result;
    }

    public enum Tag {
        RICH("rich"),
        BROKE("broke");

        public final String label;

        private Tag(String label) {
            this.label = label;
        }
    }
}
