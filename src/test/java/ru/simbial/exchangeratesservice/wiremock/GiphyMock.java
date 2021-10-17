package ru.simbial.exchangeratesservice.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import ru.simbial.exchangeratesservice.models.CurrencyExchangeRatesData;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GiphyMock {
    @Value("${giphy.gif.random.url}")
    private String apiUrl;

    @Value("${giphy.api.id}")
    private String appId;

    private static String API_URL_STATIC;

    private static String APP_ID_STATIC;

    @Value("${giphy.gif.random.url}")
    public void setUrlStatic() {
        API_URL_STATIC = apiUrl;
    }

    @Value("${giphy.api.id}")
    public void setIdStatic() {
        APP_ID_STATIC = appId;
    }


    public static void setupMockResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl()))
                .willReturn(aResponse().proxiedFrom(API_URL_STATIC)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(
                                copyToString(
                                        CurrencyExchangeRatesData.class.getClassLoader()
                                                .getResourceAsStream("rates_USD_2012_07_10.json"),
                                        defaultCharset()))));
    }

    private static String getUrl() {
        return "?api_key=" + APP_ID_STATIC + "&tag=rich&rating=g";
    }
}
