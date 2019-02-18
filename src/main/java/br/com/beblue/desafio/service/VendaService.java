package br.com.beblue.desafio.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.beblue.desafio.exception.ObjectNotFound;
import br.com.beblue.desafio.model.AlbumSpotify;
import br.com.beblue.desafio.model.RegistraVenda;
import br.com.beblue.desafio.model.Venda;
import br.com.beblue.desafio.model.enums.Classic;
import br.com.beblue.desafio.model.enums.Mpb;
import br.com.beblue.desafio.model.enums.Pop;
import br.com.beblue.desafio.model.enums.Rock;
import br.com.beblue.desafio.repository.AlbumRepository;
import br.com.beblue.desafio.repository.VendaRepository;
import br.com.beblue.desafio.service.filter.VendaFilter;

@Service
public class VendaService {

    private AlbumRepository albumRepository;
    private VendaRepository vendaRepository;

    public VendaService(AlbumRepository albumRepository,
            VendaRepository vendaRepository) {

        this.albumRepository = albumRepository;
        this.vendaRepository = vendaRepository;
    }

    public Venda registrarVenda(RegistraVenda registraVenda) throws Exception {

        AlbumSpotify album = albumRepository
                .findById(registraVenda.getIdAlbum()).orElseThrow(
                        () -> new ObjectNotFound("Album não encontrado com id: "
                                + registraVenda.getIdAlbum()));

        Venda venda = new Venda();
        venda.setNomeAlbum(album.getNome());
        venda.setDataDaCompra(registraVenda.getDiaDaCompra());
        venda.setQuantidade(registraVenda.getQuantidade());
        venda.setValorAlbum(album.getPreco().setScale(2, BigDecimal.ROUND_UP));
        venda.setValorTotal(album.getPreco()
                .multiply(BigDecimal.valueOf(registraVenda.getQuantidade()))
                .setScale(2, BigDecimal.ROUND_UP));

        switch (album.getGenero()) {
        case "pop":
            venda.setValorCashBack(Pop.obterCashBackPeloDia(
                    registraVenda.getDiaDaCompra().getDayOfWeek().name()));
            venda.setValorRetorado((Pop
                    .obterCashBackPeloDia(registraVenda.getDiaDaCompra()
                            .getDayOfWeek().name())
                    .multiply(album.getPreco()).multiply(
                            BigDecimal.valueOf(registraVenda.getQuantidade())))
                                    .setScale(2, BigDecimal.ROUND_UP));
            vendaRepository.saveAndFlush(venda);
            break;
        case "rock":
            venda.setValorCashBack(Rock.obterCashBackPeloDia(
                    registraVenda.getDiaDaCompra().getDayOfWeek().name()));
            venda.setValorRetorado((Rock
                    .obterCashBackPeloDia(registraVenda.getDiaDaCompra()
                            .getDayOfWeek().name())
                    .multiply(album.getPreco()).multiply(
                            BigDecimal.valueOf(registraVenda.getQuantidade())))
                                    .setScale(2, BigDecimal.ROUND_UP));
            vendaRepository.saveAndFlush(venda);
            break;
        case "classic":
            venda.setValorCashBack(Classic.obterCashBackPeloDia(
                    registraVenda.getDiaDaCompra().getDayOfWeek().name()));
            venda.setValorRetorado((Classic
                    .obterCashBackPeloDia(registraVenda.getDiaDaCompra()
                            .getDayOfWeek().name())
                    .multiply(album.getPreco()).multiply(
                            BigDecimal.valueOf(registraVenda.getQuantidade())))
                                    .setScale(2, BigDecimal.ROUND_UP));
            vendaRepository.saveAndFlush(venda);
            break;
        case "mpb":
            venda.setValorCashBack(Mpb.obterCashBackPeloDia(
                    registraVenda.getDiaDaCompra().getDayOfWeek().name()));
            venda.setValorRetorado((Mpb
                    .obterCashBackPeloDia(registraVenda.getDiaDaCompra()
                            .getDayOfWeek().name())
                    .multiply(album.getPreco()).multiply(
                            BigDecimal.valueOf(registraVenda.getQuantidade())))
                                    .setScale(2, BigDecimal.ROUND_UP));
            vendaRepository.saveAndFlush(venda);
            break;
        default:
            throw new ObjectNotFound(
                    "Genero não encontrado: " + album.getGenero());
        }
        return venda;

    }

    public Venda buscarVendaPorId(UUID id) throws Exception {

        return vendaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFound("Venda não encontrado: " + id));

    }

    public Page<Venda> buscarVendaComFiltros(VendaFilter vendaFilter) {

        return vendaRepository.findByDataDaCompraBetween(
                vendaFilter.getStartDate(), vendaFilter.getEndDate(),
                PageRequest.of(vendaFilter.getPage(), vendaFilter.getSize(),
                        Direction.valueOf(vendaFilter.getDirection()),
                        vendaFilter.getOrderBy()));
    }

}
