package br.com.beblue.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.beblue.desafio.config.Token;
import br.com.beblue.desafio.exception.InternalServerError;

@Service
public class SpotifyTokenService {

    @Value("${spotify.token.url}")
    private String tokenUrl;

    @Autowired
    @Qualifier("restTemplateSpotify")
    private RestTemplate restTemplate;

    private Token token;

    public String getTokenSpotify() {

        if (token == null || token.isExpired()) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("grant_type", "client_credentials");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                    map, headers);

            try {
                this.token = restTemplate.postForObject(tokenUrl, request,
                        Token.class);
            } catch (Exception e) {
                throw new InternalServerError(
                        "Error ao tentar solicitar o token do Spotify");
            }

        }

        return token.getAccessToken();
    }

}
