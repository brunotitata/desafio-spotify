package br.com.beblue.desafio.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.desafio.model.Venda;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaRepositoryTest {

    private static final String ID_VENDA = "ef9d107e-7232-43d9-be09-69cae50bbe2b";
    private static final BigDecimal VALOR_ALBUM = BigDecimal.valueOf(32.70);
    private static final BigDecimal VALOR_CASHBACK = BigDecimal.valueOf(0.25);
    private static final BigDecimal VALOR_TOTAL = BigDecimal.valueOf(32.70);
    private static final BigDecimal VALOR_RETORNADO = BigDecimal.valueOf(16.35);

    @Mock
    private VendaRepository vendaRepository;

    @Test
    public void findById() {

        given(vendaRepository.findById(UUID.fromString(ID_VENDA)))
                .willReturn(Optional.of(new Venda("Rock Malboa", VALOR_ALBUM,
                        VALOR_CASHBACK, 2, VALOR_TOTAL,
                        LocalDate.of(2019, 02, 18), VALOR_RETORNADO)));

        Venda venda = vendaRepository.findById(UUID.fromString(ID_VENDA)).get();

        verify(vendaRepository).findById(UUID.fromString(ID_VENDA));

        assertEquals(VALOR_ALBUM, venda.getValorAlbum());
        assertEquals(VALOR_CASHBACK, venda.getValorCashBack());
        assertEquals(VALOR_TOTAL, venda.getValorTotal());
        assertEquals(Integer.valueOf(2), venda.getQuantidade());
        assertEquals(LocalDate.of(2019, 02, 18), venda.getDataDaCompra());
    }

    @Test
    public void findByDataDaCompraBetween() {

        PageRequest pageable = mock(PageRequest.class);

        given(vendaRepository.findByDataDaCompraBetween(
                LocalDate.of(2019, 02, 15), LocalDate.of(2019, 02, 20),
                pageable)).willReturn(new PageImpl<Venda>(
                        Arrays.asList(new Venda("Rock Malboa", VALOR_ALBUM,
                                VALOR_CASHBACK, 2, VALOR_TOTAL,
                                LocalDate.of(2019, 02, 15), VALOR_RETORNADO),
                                new Venda("Furacao 2000", VALOR_ALBUM,
                                        VALOR_CASHBACK, 2, VALOR_TOTAL,
                                        LocalDate.of(2019, 02, 20),
                                        VALOR_RETORNADO))));

        Page<Venda> venda = vendaRepository.findByDataDaCompraBetween(
                LocalDate.of(2019, 02, 15), LocalDate.of(2019, 02, 20),
                pageable);

        verify(vendaRepository).findByDataDaCompraBetween(
                LocalDate.of(2019, 02, 15), LocalDate.of(2019, 02, 20),
                pageable);

        assertEquals(2, venda.getTotalElements());
        assertEquals("Rock Malboa", venda.getContent().get(0).getNomeAlbum());
        assertEquals(VALOR_ALBUM, venda.getContent().get(0).getValorAlbum());
        assertEquals(VALOR_CASHBACK,
                venda.getContent().get(0).getValorCashBack());
        assertEquals(Integer.valueOf(2),
                venda.getContent().get(0).getQuantidade());
        assertEquals(VALOR_TOTAL, venda.getContent().get(0).getValorTotal());
        assertEquals(LocalDate.of(2019, 02, 15),
                venda.getContent().get(0).getDataDaCompra());

        assertEquals("Furacao 2000", venda.getContent().get(1).getNomeAlbum());
        assertEquals(VALOR_ALBUM, venda.getContent().get(1).getValorAlbum());
        assertEquals(VALOR_CASHBACK,
                venda.getContent().get(1).getValorCashBack());
        assertEquals(Integer.valueOf(2),
                venda.getContent().get(1).getQuantidade());
        assertEquals(VALOR_TOTAL, venda.getContent().get(1).getValorTotal());
        assertEquals(LocalDate.of(2019, 02, 20),
                venda.getContent().get(1).getDataDaCompra());

    }

}
