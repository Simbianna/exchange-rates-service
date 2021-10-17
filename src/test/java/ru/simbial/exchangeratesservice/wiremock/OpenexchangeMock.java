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

public class OpenexchangeMock {
    @Value("${openexchangerates.api.url}")
    private String apiUrl;

    @Value("${openexchangerates.app.id}")
    private String appId;

    private static String API_URL_STATIC;

    private static String APP_ID_STATIC;

    @Value("${openexchangerates.api.url}")
    public void setUrlStatic(String url){
        API_URL_STATIC = apiUrl;
    }

    @Value("${openexchangerates.app.id}")
    public void setIdStatic(String id){
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

    private static String getUrl(){
        return "/historical/2012-07-10.json?app_id=" + APP_ID_STATIC;
    }
}
