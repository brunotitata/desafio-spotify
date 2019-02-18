package br.com.beblue.desafio.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumSpotifyTest {

    @Test
    public void criarAlbum() {

        AlbumSpotify album = new AlbumSpotify("34bqDaxqIdNbohOPdyk2RS",
                "Furacao 2000", BigDecimal.valueOf(39.90), "rock");

        assertEquals("34bqDaxqIdNbohOPdyk2RS", album.getId());
        assertEquals("Furacao 2000", album.getNome());
        assertEquals(BigDecimal.valueOf(39.90), album.getPreco());
        assertEquals("rock", album.getGenero());
    }

}
