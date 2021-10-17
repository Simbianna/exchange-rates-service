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
import ru.simbial.exchangeratesservice.wiremock.GiphyMock;
import ru.simbial.exchangeratesservice.wiremock.WireMockConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { WireMockConfig.class })
class GiphyClientTest {
    @Value("${giphy.gif.rating}")
    private String rating;

    @Value("${giphy.api.id}")
    private String app_id;

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    private GiphyClient giphyClient;

    @BeforeEach
    void setUp() throws IOException {
        GiphyMock.setupMockResponse(wireMockServer);
    }

    @AfterEach
    void shutDown(){
        wireMockServer.shutdownServer();
    }

    @Test
    public void testReturnStatusOk() throws Exception {
        assertTrue(giphyClient.getRandomGifByTag(app_id, "RICH", rating)
        .getStatusCode().is2xxSuccessful());
    }
}