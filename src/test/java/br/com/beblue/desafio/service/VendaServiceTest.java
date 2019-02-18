package br.com.beblue.desafio.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.model.RegistraVenda;
import br.com.beblue.desafio.model.Venda;
import br.com.beblue.desafio.repository.AlbumRepository;
import br.com.beblue.desafio.repository.VendaRepository;
import br.com.beblue.desafio.service.filter.VendaFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaServiceTest {

    private static final String ID_VENDA = "ef9d107e-7232-43d9-be09-69cae50bbe2b";
    private static final String ID_ALBUM = "34bqDaxqIdNbohOPdyk2RS";
    private static final BigDecimal VALOR_ALBUM = BigDecimal.valueOf(92.55);
    private static final BigDecimal VALOR_CASHBACK = BigDecimal.valueOf(15.85);
    private static final BigDecimal VALOR_TOTAL = BigDecimal.valueOf(92.55);

    @MockBean
    private VendaService vendaService;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private VendaRepository vendaRepository;

    @Before
    public void setUp() {
        this.vendaService = new VendaService(albumRepository, vendaRepository);
    }

    @Test
    public void registrarVenda() throws Exception {

        AlbumSpotify albumSpotify = mock(AlbumSpotify.class);
        RegistraVenda registraVenda = mock(RegistraVenda.class);
        Venda venda = mock(Venda.class);

        given(registraVenda.getIdAlbum()).willReturn(ID_ALBUM);
        given(registraVenda.getQuantidade()).willReturn(1);
        given(registraVenda.getDiaDaCompra())
                .willReturn(LocalDate.of(2019, 02, 18));
        given(albumSpotify.getPreco()).willReturn(VALOR_ALBUM);
        given(albumSpotify.getGenero()).willReturn("pop");
        given(venda.getValorTotal()).willReturn(VALOR_TOTAL);
        given(venda.getValorCashBack()).willReturn(VALOR_CASHBACK);
        given(venda.getValorTotal()).willReturn(VALOR_TOTAL);

        given(albumRepository.findById(ID_ALBUM))
                .willReturn(Optional.of(albumSpotify));

        albumRepository.findById(ID_ALBUM);

        given(vendaService.registrarVenda(registraVenda)).willReturn(venda);

        Venda registrarVenda = vendaService.registrarVenda(registraVenda);

        verify(vendaRepository, times(1)).saveAndFlush(registrarVenda);

    }

    @Test
    public void buscarVendaPorId() throws Exception {

        Venda venda = mock(Venda.class);

        given(vendaRepository.findById(UUID.fromString(ID_VENDA)))
                .willReturn(Optional.of(venda));

        vendaService.buscarVendaPorId(UUID.fromString(ID_VENDA));

        verify(vendaRepository).findById(UUID.fromString(ID_VENDA));

    }

    @Test
    public void buscarVendaComFiltros() {

        Venda venda = mock(Venda.class);
        PageRequest pageRequest = mock(PageRequest.class);
        VendaFilter vendaFilter = mock(VendaFilter.class);

        given(vendaFilter.getDirection()).willReturn("ASC");
        given(vendaFilter.getOrderBy()).willReturn("dataDaCompra");
        given(vendaFilter.getStartDate())
                .willReturn(LocalDate.of(2019, 02, 13));
        given(vendaFilter.getEndDate()).willReturn(LocalDate.of(2019, 02, 13));
        given(vendaFilter.getPage()).willReturn(0);
        given(vendaFilter.getSize()).willReturn(20);

        given(vendaRepository.findByDataDaCompraBetween(
                vendaFilter.getStartDate(), vendaFilter.getEndDate(),
                pageRequest))
                        .willReturn(new PageImpl<Venda>(Arrays.asList(venda)));

        vendaService.buscarVendaComFiltros(vendaFilter);

        verify(vendaRepository).findByDataDaCompraBetween(
                vendaFilter.getStartDate(), vendaFilter.getEndDate(),
                PageRequest.of(vendaFilter.getPage(), vendaFilter.getSize(),
                        Direction.valueOf(vendaFilter.getDirection()),
                        vendaFilter.getOrderBy()));

    }

}
