package com.auth.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RemitoRequest {
    private String numeroRemito;
    private LocalDate fechaRemito;
    private List<MaterialRequest> materiales;
}

@Data
class MaterialRequest {
    private String nombre;
    private Integer cantidad;
    private String unidad;
}