package com.br.veiculos.verzel.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Component
public class ValueUpdater {
    public void updateIfNotNullOrEmpty(String value, Consumer<String> setter) {
        if(value != null && !value.isBlank()) {
            setter.accept(value);
        }
    }

    public void updateIfNotNullOrEmpty(BigDecimal value, Consumer<BigDecimal> setter) {
        if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
            setter.accept(value);
        }
    }
}
