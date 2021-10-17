package ru.simbial.exchangeratesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simbial.exchangeratesservice.models.CurrencyExchangeRatesData;

import java.util.Map;

@FeignClient(name = "openexchangerates.org-service", url = "${openexchangerates.api.url}")
public interface OpenexchangeratesClient {

    @GetMapping("latest.json")
    ResponseEntity<CurrencyExchangeRatesData> getLatestRatesByCurrency(
            @RequestParam("app_id") String app_id,
            @RequestParam("base") String base);


    @GetMapping("historical/{date}.json")
    ResponseEntity<CurrencyExchangeRatesData> getRatesByCurrencyByDate(
            @PathVariable(name = "date") String date,
            @RequestParam("app_id") String app_id,
            @RequestParam("base") String base);

    @GetMapping("currencies.json")
    ResponseEntity<Map<String, String>> getAvailableCurrenciesMap(
            @RequestParam("app_id") String app_id);

}
