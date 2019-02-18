package br.com.beblue.desafio.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Utils {

    public static BigDecimal gerarPrecoRandomicamente() {
        return new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)),
                2);
    }

}
