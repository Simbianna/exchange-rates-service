package ru.simbial.exchangeratesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy.com-service", url = "${giphy.gif.random.url}")
public interface GiphyClient {

    @GetMapping()
    ResponseEntity<String> getRandomGifByTag(
            @RequestParam(name = "api_key") String api_key,
            @RequestParam("tag") String tag,
            @RequestParam(name = "rating") String rating
    );

}
