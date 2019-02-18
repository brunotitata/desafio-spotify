package br.com.beblue.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.desafio.service.SpotifyService;

@RestController
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/download")
    public ResponseEntity<?> buscarAlbums() {

        spotifyService.execute();

        return ResponseEntity.ok().build();
    }

}
