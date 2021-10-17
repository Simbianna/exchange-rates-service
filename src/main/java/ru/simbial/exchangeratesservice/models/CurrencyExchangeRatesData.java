package ru.simbial.exchangeratesservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeRatesData {
    private String disclaimer;
    private String license;
    private String Base;
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public String getBase() {
        return Base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyExchangeRatesData)) return false;
        CurrencyExchangeRatesData that = (CurrencyExchangeRatesData) o;
        return Objects.equals(getDisclaimer(), that.getDisclaimer()) &&
                Objects.equals(getLicense(), that.getLicense()) &&
                Objects.equals(getBase(), that.getBase()) &&
                Objects.equals(getRates(), that.getRates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDisclaimer(), getLicense(), getBase(), getRates());
    }
}
