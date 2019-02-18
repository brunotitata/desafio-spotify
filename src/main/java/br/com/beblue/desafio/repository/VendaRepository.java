package br.com.beblue.desafio.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.beblue.desafio.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {

    Optional<Venda> findById(UUID id);

    Page<Venda> findByDataDaCompraBetween(LocalDate startDate,
            LocalDate endDate, Pageable pageable);

}
