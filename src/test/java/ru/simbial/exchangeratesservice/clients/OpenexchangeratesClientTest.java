package ru.simbial.exchangeratesservice.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.simbial.exchangeratesservice.wiremock.OpenexchangeMock;
import ru.simbial.exchangeratesservice.wiremock.WireMockConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.simbial.exchangeratesservice.testData.OpenexhangeratesTestData.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { WireMockConfig.class })
class OpenexchangeratesClientTest {

    @Value("${openexchangerates.app.id}")
    private String appId;

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    private OpenexchangeratesClient openexchangeratesClient;

    @BeforeEach
    void setUp() throws IOException {
        OpenexchangeMock.setupMockResponse(wireMockServer);
    }

    @AfterEach
    void shutDown(){
        wireMockServer.shutdownServer();
    }

    @Test
    public void testReturnDataIsTrue() throws Exception {
        assertEquals(openexchangeratesClient
                        .getRatesByCurrencyByDate(DATE_2012_07_10, appId, "USD")
                        .getBody()
                , USD_2012_07_10_DATA);
    }

}