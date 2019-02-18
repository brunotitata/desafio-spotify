package br.com.beblue.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.service.AlbumService;
import br.com.beblue.desafio.service.filter.GeneroFilter;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService service;

    @GetMapping("/buscar")
    public ResponseEntity<Page<AlbumSpotify>> buscarAlbumPorGenero(
            @RequestParam(name = "genero", required = true) String genero,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

        return ResponseEntity.ok().body(service.buscarTodosAlbumsPorGenero(
                new GeneroFilter(genero, page, size, orderBy, direction)));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlbumSpotify> buscarAlbumPorId(
            @PathVariable(value = "id") String id) throws Exception {

        return ResponseEntity.ok().body(service.buscarAlbumPeloId(id));
    }

}
