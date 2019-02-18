package br.com.beblue.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.beblue.desafio.exception.ObjectNotFound;
import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.repository.AlbumRepository;
import br.com.beblue.desafio.service.filter.GeneroFilter;

@Service
public class AlbumService {

    private AlbumRepository repository;

    public AlbumService(AlbumRepository albumRepository) {
        this.repository = albumRepository;
    }

    public AlbumSpotify buscarAlbumPeloId(String id) throws Exception {

        return repository.findById(id).orElseThrow(() -> new ObjectNotFound(
                "Não foi possivel encontrar o album pelo ID: " + id));
    }

    public Page<AlbumSpotify> buscarTodosAlbumsPorGenero(GeneroFilter genero) {

        PageRequest pageRequest = PageRequest.of(genero.getPage(),
                genero.getSize(), Direction.valueOf(genero.getDirection()),
                genero.getOrderBy());

        return repository.findByGenero(genero.getGenero().toLowerCase(),
                pageRequest);
    }

}
