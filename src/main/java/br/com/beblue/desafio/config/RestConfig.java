package br.com.beblue.desafio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Value("${spotify.client.id}")
    private String spotifyClientId;

    @Value("${spotify.client.secret}")
    private String spotifyClientSecret;

    @Bean
    public RestTemplate restTemplateSpotify() {

        final RestTemplate template = new RestTemplate();

        template.getInterceptors().add(new BasicAuthorizationInterceptor(
                spotifyClientId, spotifyClientSecret));

        return template;
    }

}
