package com.auth.repository;

import com.auth.model.Remito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RemitoRepository extends JpaRepository<Remito, Long> {
    Optional<Remito> findByNumeroRemito(String numeroRemito);
    boolean existsByNumeroRemito(String numeroRemito);
}