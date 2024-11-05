-- Agregar las tablas para remitos y materiales
CREATE TABLE IF NOT EXISTS remitos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_remito VARCHAR(255) NOT NULL UNIQUE,
    fecha_remito DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS materiales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL,
    unidad VARCHAR(50) NOT NULL,
    remito_id BIGINT NOT NULL,
    FOREIGN KEY (remito_id) REFERENCES remitos(id)
);