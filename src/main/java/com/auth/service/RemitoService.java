package com.auth.service;

import com.auth.dto.RemitoRequest;
import com.auth.model.Remito;
import com.auth.model.Material;
import com.auth.repository.RemitoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemitoService {

    private final RemitoRepository remitoRepository;

    @Transactional
    public Remito crearRemito(RemitoRequest request) {
        if (remitoRepository.existsByNumeroRemito(request.getNumeroRemito())) {
            throw new IllegalArgumentException("Ya existe un remito con ese número");
        }

        Remito remito = new Remito();
        remito.setNumeroRemito(request.getNumeroRemito());
        remito.setFechaRemito(request.getFechaRemito());

        List<Material> materiales = request.getMateriales().stream()
            .map(materialDto -> {
                Material material = new Material();
                material.setNombre(materialDto.getNombre());
                material.setCantidad(materialDto.getCantidad());
                material.setUnidad(materialDto.getUnidad());
                material.setRemito(remito);
                return material;
            })
            .collect(Collectors.toList());

        remito.setMateriales(materiales);
        return remitoRepository.save(remito);
    }

    public List<Remito> obtenerTodosLosRemitos() {
        return remitoRepository.findAll();
    }

    public Remito obtenerRemitoPorNumero(String numeroRemito) {
        return remitoRepository.findByNumeroRemito(numeroRemito)
            .orElseThrow(() -> new EntityNotFoundException("Remito no encontrado"));
    }
}