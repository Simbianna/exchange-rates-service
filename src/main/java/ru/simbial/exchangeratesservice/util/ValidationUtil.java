package ru.simbial.exchangeratesservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class ValidationUtil {

    public static <T> boolean checkResponseEntityIsOkAndHasBody(ResponseEntity<T> responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK
                && responseEntity.hasBody()
                && responseEntity.getBody() != null;
    }

    /*public static boolean checkCurrencyCodeIsPresented(String currencyCode, Collection<String> currencies) {
        return (currencies.contains(currencyCode));
    }*/
}
