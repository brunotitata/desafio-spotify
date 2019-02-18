package br.com.beblue.desafio.model.enums;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.beblue.desafio.exception.IllegalArgumentException;

public enum Rock {

    /**
     * format checkstyle
     */
    MONDAY(0.10),
    /**
     * format checkstyle
     */
    TUESDAY(0.15),
    /**
     * format checkstyle
     */
    WEDNESDAY(0.15),
    /**
     * format checkstyle
     */
    THURSDAY(0.15),
    /**
     * format checkstyle
     */
    FRIDAY(0.20),
    /**
     * format checkstyle
     */
    SATURDAY(0.40),
    /**
     * format checkstyle
     */
    SUNDAY(0.40);
    /**
     * format checkstyle
     */

    private Double cashback;

    Rock(Double cashback) {
        this.cashback = cashback;
    }

    public Double getCashback() {
        return cashback;
    }

    public static BigDecimal obterCashBackPeloDia(String data) {

        List<Rock> pop = Arrays.asList(Rock.values());
        return pop.stream().filter(a -> a.name().equals(data))
                .map(m -> new BigDecimal(m.getCashback())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não foi possivel capturar o cashback do dia: "
                                + data));
    }

}
