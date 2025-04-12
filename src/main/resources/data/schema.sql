-- Drop database if exists (cuidado con esto en producción)
-- DROP DATABASE IF EXISTS proyects_db;

-- Create database
-- CREATE DATABASE proyects_db;

-- Tabla para proyectos
DROP TABLE IF EXISTS projects;

CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    fondo VARCHAR(1000),
    concurso VARCHAR(1000),
    anio INTEGER,
    contrato VARCHAR(1000),
    titulo VARCHAR(1000),
    fecha_inicio DATE,
    fecha_fin DATE,
    nombre_solicitante VARCHAR(1000),
    distrito VARCHAR(1000),
    provincia VARCHAR(1000),
    departamento VARCHAR(1000),
    ubigeo VARCHAR(20),
    monto_rnr DOUBLE PRECISION,
    monto_financiero DOUBLE PRECISION,
    monto_no_financiero DOUBLE PRECISION,
    moneda VARCHAR(10),
    fecha_corte DATE
);