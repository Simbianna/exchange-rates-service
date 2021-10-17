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

    /*Ввиду того, что API предоставляет бесплатный доступ только к курсам по отношению к доллару
    currency_code будет измнен на значение по умолчанию(USD)
    */
    @GetMapping(value = "reaction/{currency_code}", produces = "image/webp")
    public ResponseEntity<byte[]> getGifReactionToExchangeRateChanges(
            @PathVariable() String currency_code) {

        /*При использовании openexchangerates с уровнем не ниже The Developer Plan
         зкомментировать строчку ниже*/
        currency_code = "USD";

        byte[] mediaBytes = currencyChangesReactionService
                .getGifReactionToExchangeRateChanges(currency_code.toUpperCase());

        return mediaBytes != null
                ? new ResponseEntity<>(mediaBytes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

