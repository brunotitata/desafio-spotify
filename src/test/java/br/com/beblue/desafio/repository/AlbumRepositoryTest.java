package br.com.beblue.desafio.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.desafio.model.AlbumSpotify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRepositoryTest {

    private static final String ID_ALBUM = "34bqDaxqIdNbohOPdyk2RS";

    @Mock
    private AlbumRepository albumRepository;

    @Test
    public void findById() {

        given(albumRepository.findById(ID_ALBUM))
                .willReturn(Optional.of(new AlbumSpotify(ID_ALBUM, "nome album",
                        BigDecimal.valueOf(10.00), "rock")));

        AlbumSpotify album = albumRepository.findById(ID_ALBUM).get();

        verify(albumRepository).findById(ID_ALBUM);

        assertEquals(ID_ALBUM, album.getId());
        assertEquals("nome album", album.getNome());
        assertEquals(BigDecimal.valueOf(10.00), album.getPreco());
        assertEquals("rock", album.getGenero());

    }

    @Test
    public void findByGenero() {

        PageRequest pageable = mock(PageRequest.class);

        given(albumRepository.findByGenero("rock", pageable))
                .willReturn(new PageImpl<AlbumSpotify>(Arrays.asList(
                        new AlbumSpotify(ID_ALBUM, "Rock Brasil",
                                BigDecimal.valueOf(10.00), "rock"),
                        new AlbumSpotify("34bqDaxqIdNheDHAuHDUS",
                                "Rock in Roll", BigDecimal.valueOf(15.89),
                                "rock"))));

        List<AlbumSpotify> album = albumRepository
                .findByGenero("rock", pageable).getContent();

        verify(albumRepository).findByGenero("rock", pageable);

        assertEquals(ID_ALBUM, album.get(0).getId());
        assertEquals("Rock Brasil", album.get(0).getNome());
        assertEquals(BigDecimal.valueOf(10.00), album.get(0).getPreco());
        assertEquals("rock", album.get(0).getGenero());

        assertEquals("34bqDaxqIdNheDHAuHDUS", album.get(1).getId());
        assertEquals("Rock in Roll", album.get(1).getNome());
        assertEquals(BigDecimal.valueOf(15.89), album.get(1).getPreco());
        assertEquals("rock", album.get(1).getGenero());
    }

}
