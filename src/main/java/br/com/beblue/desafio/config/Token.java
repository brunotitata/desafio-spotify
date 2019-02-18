package br.com.beblue.desafio.config;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    private LocalDateTime expirationTime;

    public Token(String accessToken, Integer expiresIn) {
        setAccessToken(accessToken);
        setExpiresIn(expiresIn);
    }

    @SuppressWarnings("unused")
    private Token() {
    }

    public String getAccessToken() {
        this.expirationTime = LocalDateTime.now().plusSeconds(expiresIn);
        return accessToken;
    }

    public boolean isExpired() {
        return expirationTime.isBefore(LocalDateTime.now());
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

}
