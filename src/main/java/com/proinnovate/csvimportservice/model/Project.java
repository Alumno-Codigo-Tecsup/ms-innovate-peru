package com.proinnovate.csvimportservice.model;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "FONDO")
    @Column(length = 1000)
    private String fondo;

    @CsvBindByName(column = "CONCURSO")
    @Column(length = 1000)
    private String concurso;

    @CsvBindByName(column = "ANIO")
    private Integer anio;

    @CsvBindByName(column = "CONTRATO")
    @Column(length = 1000)
    private String contrato;

    @CsvBindByName(column = "TITULO")
    @Column(length = 1000)
    private String titulo;

    @CsvBindByName(column = "FECHA_INICIO")
    @CsvDate(value = "yyyyMMdd")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @CsvBindByName(column = "FECHA_FIN")
    @CsvDate(value = "yyyyMMdd")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @CsvBindByName(column = "NOMBRE_SOLICITANTE")
    @Column(length = 1000)
    private String nombreSolicitante;

    @CsvBindByName(column = "DISTRITO")
    @Column(length = 1000)
    private String distrito;

    @CsvBindByName(column = "PROVINCIA")
    @Column(length = 1000)
    private String provincia;

    @CsvBindByName(column = "DEPARTAMENTO")
    @Column(length = 1000)
    private String departamento;

    @CsvBindByName(column = "UBIGEO")
    private String ubigeo;

    @CsvBindByName(column = "MONTO_RNR")
    @Column(name = "monto_rnr")
    private Double montoRNR;

    @CsvBindByName(column = "MONTO_FINANCIERO")
    private Double montoFinanciero;

    @CsvBindByName(column = "MONTO_NO_FINANCIERO")
    private Double montoNoFinanciero;

    @CsvBindByName(column = "MONEDA")
    private String moneda;

    @CsvBindByName(column = "FECHA_CORTE")
    @CsvDate(value = "yyyyMMdd")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;

}
