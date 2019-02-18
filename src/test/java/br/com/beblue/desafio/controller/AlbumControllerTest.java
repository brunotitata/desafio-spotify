package br.com.beblue.desafio.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.repository.AlbumRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private AlbumRepository albumRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void buscarAlbumPorGenero() throws Exception {

        AlbumSpotify albumSpotify = mock(AlbumSpotify.class);

        given(albumRepository.saveAndFlush(albumSpotify)).willReturn(
                new AlbumSpotify("2euw8jfisnf82nc", "teste",
                        BigDecimal.valueOf(323.32), "pop"),
                new AlbumSpotify("2e45tyhgfd", "teste2",
                        BigDecimal.valueOf(623.32), "rock"));

        mockMvc.perform(get("/buscar?genero=rock")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")).andExpect(status().isOk());

    }

}
