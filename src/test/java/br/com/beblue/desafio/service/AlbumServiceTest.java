package br.com.beblue.desafio.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.desafio.exception.ObjectNotFound;
import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.repository.AlbumRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    private static final String ID_ALBUM = "34bqDaxqIdNbohOPdyk2RS";

    @MockBean
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @Before
    public void setUp() {
        this.albumService = new AlbumService(albumRepository);
    }

    @Test
    public void buscarAlbumPeloId() throws Exception {

        AlbumSpotify albumSpotify = mock(AlbumSpotify.class);

        given(albumRepository.findById(ID_ALBUM))
                .willReturn(Optional.of(albumSpotify));

        albumService.buscarAlbumPeloId(ID_ALBUM);

        verify(albumRepository).findById(ID_ALBUM);
    }

    @Test
    public void buscarAlbumPeloIdENaoEncontrarDeveLancarException()
            throws Exception {

        given(albumRepository.findById(ID_ALBUM)).willReturn(Optional.empty());

        albumRepository.findById(ID_ALBUM);

        assertThatThrownBy(() -> albumService.buscarAlbumPeloId(ID_ALBUM))
                .hasMessage("Não foi possivel encontrar o album pelo ID: "
                        + ID_ALBUM)
                .isInstanceOf(ObjectNotFound.class);

    }

}
