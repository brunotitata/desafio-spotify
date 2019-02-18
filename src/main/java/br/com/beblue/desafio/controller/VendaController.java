package br.com.beblue.desafio.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.beblue.desafio.model.RegistraVenda;
import br.com.beblue.desafio.model.Venda;
import br.com.beblue.desafio.service.VendaService;
import br.com.beblue.desafio.service.filter.VendaFilter;

@RestController
public class VendaController {

    @Autowired
    private VendaService service;

    @PostMapping("/venda")
    public ResponseEntity<Venda> registrarUmaNovaVenda(
            @RequestBody RegistraVenda registraVenda) throws Exception {

        Venda venda = service.registrarVenda(registraVenda);

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(venda.getId()).toUri())
                .build();

    }

    @GetMapping("/venda/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable String id)
            throws Exception {

        return ResponseEntity.ok()
                .body(service.buscarVendaPorId(UUID.fromString(id)));

    }

    @GetMapping("/venda")
    public ResponseEntity<Page<Venda>> buscarVendaComFiltros(
            @RequestParam(value = "startDate", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
            @RequestParam(name = "orderBy", defaultValue = "dataDaCompra") String orderBy,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction) {

        return ResponseEntity.ok()
                .body(service.buscarVendaComFiltros(new VendaFilter(startDate,
                        endDate, orderBy, page, size, direction)));
    }

}
