package br.com.beblue.desafio.model.enums;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.beblue.desafio.exception.IllegalArgumentException;

public enum Classic {

    /**
     * format checkstyle
     */
    MONDAY(0.03),
    /**
     * format checkstyle
     */
    TUESDAY(0.05),
    /**
     * format checkstyle
     */
    WEDNESDAY(0.08),
    /**
     * format checkstyle
     */
    THURSDAY(0.13),
    /**
     * format checkstyle
     */
    FRIDAY(0.18),
    /**
     * format checkstyle
     */
    SATURDAY(0.25),
    /**
     * format checkstyle
     */
    SUNDAY(0.35);
    /**
     * format checkstyle
     */

    private Double cashback;

    Classic(Double cashback) {
        this.cashback = cashback;
    }

    public Double getCashback() {
        return cashback;
    }

    public static BigDecimal obterCashBackPeloDia(String data) {

        return Arrays.asList(Classic.values()).stream()
                .filter(a -> a.name().equals(data))
                .map(m -> new BigDecimal(m.getCashback())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não foi possivel capturar o cashback do dia: "
                                + data));
    }

}
