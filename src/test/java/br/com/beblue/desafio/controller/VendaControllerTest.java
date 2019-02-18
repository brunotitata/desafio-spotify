package br.com.beblue.desafio.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.beblue.desafio.model.RegistraVenda;
import br.com.beblue.desafio.model.Venda;
import br.com.beblue.desafio.repository.AlbumRepository;
import br.com.beblue.desafio.repository.VendaRepository;
import br.com.beblue.desafio.service.VendaService;
import br.com.beblue.desafio.service.filter.VendaFilter;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VendaController.class)
public class VendaControllerTest {

    private static final String ID_VENDA = "bc231e59-5015-4811-9095-e790752f85f4";

    private static final String NOME_ALBUM = "Five Songs Of Christmas - Pop";
    private static final BigDecimal VALOR_ALBUM = BigDecimal.valueOf(92.55);
    private static final BigDecimal VALOR_CASHBACK = BigDecimal.valueOf(0.25);
    private static final BigDecimal VALOR_TOTAL = BigDecimal.valueOf(92.55);
    private static final BigDecimal VALOR_RETORNO = BigDecimal.valueOf(15.85);

    @Autowired
    private MockMvc mockMvc;

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
    @Ignore
    public void registrarUmaNovaVenda() throws Exception {

        doNothing().when(vendaService).registrarVenda(new RegistraVenda(
                "65P84ZBtHARyccEPc4XFrj", LocalDate.of(2019, 02, 18), 1));

        String payload = "{\n"
                + "        \"idAlbum\": \"65P84ZBtHARyccEPc4XFrj\",\n"
                + "        \"diaDaCompra\" : \"SABADO\",\n"
                + "        \"quantidade\": 1\n" + "}";

        mockMvc.perform(post("/venda").content(payload)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @Ignore
    public void buscarVendaPorId() throws Exception {

        given(vendaService.buscarVendaPorId(UUID.fromString(ID_VENDA)))
                .willReturn(new Venda(NOME_ALBUM, VALOR_ALBUM, VALOR_CASHBACK,
                        1, VALOR_TOTAL, LocalDate.now(), VALOR_RETORNO));

        mockMvc.perform(get("/venda/" + ID_VENDA)).andExpect(status().isOk());
    }

    @Test
    public void buscarVendaComFiltros() throws Exception {

        List<Venda> asList = Arrays.asList(new Venda("Discotecagem Pop Variada",
                BigDecimal.valueOf(95.16), BigDecimal.valueOf(38.06), 2,
                BigDecimal.valueOf(190.32), LocalDate.of(2019, 02, 18),
                BigDecimal.valueOf(12.25)),
                new Venda("Five Songs Of Christmas - Pop",
                        BigDecimal.valueOf(92.00), BigDecimal.valueOf(18.06), 1,
                        BigDecimal.valueOf(92.00), LocalDate.of(2019, 02, 20),
                        BigDecimal.valueOf(9.85)));

        given(vendaService.buscarVendaComFiltros(new VendaFilter(
                LocalDate.of(2019, 02, 18), LocalDate.of(2019, 02, 20),
                "dataDaCompra", 0, 20, "DESC"))).willReturn(
                        new PageImpl<Venda>(asList, new PageRequest(0, 20,
                                Direction.fromString("DESC"), "dataDaCompra"),
                                asList.size()));

        String result = mockMvc
                .perform(get("/venda").contentType(MediaType.APPLICATION_JSON)
                        .param("startDate", "2019-02-18")
                        .param("endDate", "2019-02-20"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertNotNull(result);

    }

}
