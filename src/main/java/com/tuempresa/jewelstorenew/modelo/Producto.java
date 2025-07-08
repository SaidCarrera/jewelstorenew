package com.tuempresa.jewelstorenew.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50) @Required
    private String nombre;

    @Column(length = 30)
    private String tipo;

    @Column(length = 30)
    private String material;

    @Column @Required
    private BigDecimal precio;

    @Column(length = 255)
    private String descripcion;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;
}
