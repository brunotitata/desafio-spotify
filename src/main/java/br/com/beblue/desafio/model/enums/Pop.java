package br.com.beblue.desafio.model.enums;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.beblue.desafio.exception.IllegalArgumentException;

public enum Pop {

    /**
     * format checkstyle
     */
    MONDAY(0.07),
    /**
     * format checkstyle
     */
    TUESDAY(0.06),
    /**
     * format checkstyle
     */
    WEDNESDAY(0.02),
    /**
     * format checkstyle
     */
    THURSDAY(0.10),
    /**
     * format checkstyle
     */
    FRIDAY(0.15),
    /**
     * format checkstyle
     */
    SATURDAY(0.20),
    /**
     * format checkstyle
     */
    SUNDAY(0.25);
    /**
     * format checkstyle
     */

    private Double cashback;

    Pop(Double cashback) {
        this.cashback = cashback;
    }

    public Double getCashback() {
        return cashback;
    }

    public static BigDecimal obterCashBackPeloDia(String data) {

        return Arrays.asList(Pop.values()).stream()
                .filter(a -> a.name().equals(data))
                .map(m -> new BigDecimal(m.getCashback())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não foi possivel capturar o cashback do dia: "
                                + data));
    }

}
