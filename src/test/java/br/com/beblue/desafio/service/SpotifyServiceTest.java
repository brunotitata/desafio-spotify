package br.com.beblue.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.beblue.desafio.model.AlbumResource;
import br.com.beblue.desafio.model.AlbumResource.Album;
import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.repository.AlbumRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpotifyServiceTest {

    @Mock
    private SpotifyTokenService spotifyTokenService;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void execute() throws Exception {

        AlbumSpotify albumSpotify = mock(AlbumSpotify.class);

        given(spotifyTokenService.getTokenSpotify()).willReturn(
                "BQBP9QQDKvyhJeA5tCc41hHwpQ5aQGnnC-bfr8siIN5iOJhowPOjcT86aItxkEYWIm-DjcZ4rSZcu-HV82M");

        given(restTemplate.exchange(eq(
                "https://api.spotify.com/v1/search?q=rock&type=album&market=BR&limit=50"),
                eq(HttpMethod.GET), any(HttpEntity.class),
                eq(AlbumResource.class)))
                        .willReturn(new ResponseEntity<AlbumResource>(
                                new AlbumResource(new Album(Arrays.asList(
                                        new AlbumResource.Album.Item(
                                                "34bqDaxqIdNbohOPdyk2RS",
                                                "Discotecagem Pop Variada"),
                                        new AlbumResource.Album.Item(
                                                "3KzbSN2H2bqf9b8NKiCIL0",
                                                "Don't Feed The Pop Monster")))),
                                HttpStatus.OK));

        albumRepository.saveAndFlush(albumSpotify);

        verify(albumRepository).saveAndFlush(albumSpotify);

    }

}
