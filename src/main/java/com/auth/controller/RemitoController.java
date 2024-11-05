package com.auth.controller;

import com.auth.dto.RemitoRequest;
import com.auth.model.Remito;
import com.auth.service.RemitoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remitos")
@RequiredArgsConstructor
public class RemitoController {

    private final RemitoService remitoService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Remito> crearRemito(@Valid @RequestBody RemitoRequest request) {
        Remito remito = remitoService.crearRemito(request);
        return ResponseEntity.ok(remito);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Remito>> obtenerRemitos() {
        List<Remito> remitos = remitoService.obtenerTodosLosRemitos();
        return ResponseEntity.ok(remitos);
    }

    @GetMapping("/{numeroRemito}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Remito> obtenerRemitoPorNumero(@PathVariable String numeroRemito) {
        Remito remito = remitoService.obtenerRemitoPorNumero(numeroRemito);
        return ResponseEntity.ok(remito);
    }
}