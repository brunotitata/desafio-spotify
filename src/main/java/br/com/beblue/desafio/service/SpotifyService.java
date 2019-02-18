package br.com.beblue.desafio.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.beblue.desafio.exception.InternalServerError;
import br.com.beblue.desafio.model.AlbumResource;
import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.repository.AlbumRepository;
import br.com.beblue.desafio.utils.Utils;

@Service
public class SpotifyService {

    private String search;
    private SpotifyTokenService tokenService;
    private AlbumRepository repository;
    private RestTemplate restTemplate;

    public SpotifyService(@Value("${spotify.search.url}") String search,
            SpotifyTokenService tokenService, AlbumRepository repository,
            RestTemplate restTemplate) {
        this.search = search;
        this.tokenService = tokenService;
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public void execute() {
        for (String genero : listarGeneros()) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization",
                    "Bearer " + tokenService.getTokenSpotify());

            HttpEntity<String> entity = new HttpEntity<String>(headers);
            UriComponentsBuilder builder = montarUri(genero);
            ResponseEntity<AlbumResource> response = http(entity, builder);

            response.getBody().getAlbum().getItems()
                    .forEach(album -> repository.saveAndFlush(
                            new AlbumSpotify(album.getId(), album.getName(),
                                    Utils.gerarPrecoRandomicamente(), genero)));
        }
    }

    private ResponseEntity<AlbumResource> http(HttpEntity<String> entity,
            UriComponentsBuilder builder) {

        return Optional
                .ofNullable(restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, entity, AlbumResource.class))
                .orElseThrow(() -> new InternalServerError(
                        "Error ao tentar buscar albums na API do Spotify"));
    }

    private UriComponentsBuilder montarUri(String genero) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(search);
        builder.queryParam("q", genero);
        builder.queryParam("type", "album");
        builder.queryParam("market", "BR");
        builder.queryParam("limit", "50");
        return builder;
    }

    private List<String> listarGeneros() {
        return Arrays.asList("pop", "rock", "classic", "mpb");
    }
}
