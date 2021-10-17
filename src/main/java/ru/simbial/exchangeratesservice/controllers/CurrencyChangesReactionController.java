package ru.simbial.exchangeratesservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.simbial.exchangeratesservice.services.CurrencyChangesReactionService;

@RestController
public class CurrencyChangesReactionController {
    private CurrencyChangesReactionService currencyChangesReactionService;

    @Autowired
    public void setCurrencyChangesReactionService(CurrencyChangesReactionService currencyChangesReactionService) {
        this.currencyChangesReactionService = currencyChangesReactionService;
    }

    @GetMapping(produces = "application/json")
    public String greetingsController(){
       return "Hello, please go to: http://localhost:8880/reaction/USD";
    }

    /*Ввиду того, что API предоставляет бесплатный доступ только к курсам по отношению к доллару
    с текущим openexchangerates.app.id будет работать только url, где
    currency_code = USD
    */
    @GetMapping(value = "reaction/{currency_code}", produces = "image/webp")
    public ResponseEntity<byte[]> getGifReactionToExchangeRateChanges(
            @PathVariable() String currency_code) {

        byte[] mediaBytes = currencyChangesReactionService
                .getGifReactionToExchangeRateChanges(currency_code.toUpperCase());

        return mediaBytes != null
                ? new ResponseEntity<>(mediaBytes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

