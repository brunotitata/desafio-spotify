package br.com.beblue.desafio.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaTest {

    private static final String NOME_ALBUM = "Gaiola das popozudas";
    private static final BigDecimal VALOR_ALBUM = BigDecimal.valueOf(92.55);
    private static final BigDecimal VALOR_CASHBACK = BigDecimal.valueOf(0.25);
    private static final BigDecimal VALOR_TOTAL = BigDecimal.valueOf(92.55);
    private static final BigDecimal VALOR_RETORNADO = BigDecimal.valueOf(15.85);

    @Test
    public void criarVenda() {

        Venda venda = new Venda(NOME_ALBUM, VALOR_ALBUM, VALOR_CASHBACK, 1,
                VALOR_TOTAL, LocalDate.now(), VALOR_RETORNADO);

        assertEquals(NOME_ALBUM, venda.getNomeAlbum());
        assertEquals(VALOR_ALBUM, venda.getValorAlbum());
        assertEquals(VALOR_CASHBACK, venda.getValorCashBack());
        assertEquals(VALOR_TOTAL, venda.getValorTotal());
        assertEquals(LocalDate.now(), venda.getDataDaCompra());
        assertEquals(Integer.valueOf(1), venda.getQuantidade());
        assertEquals(VALOR_RETORNADO, venda.getValorRetornado());
    }

}
