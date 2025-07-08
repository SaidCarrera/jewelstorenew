package com.tuempresa.jewelstorenew.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50) @Required
    private String nombre;

    @Column(length = 15)
    private String cedula;

    @Column(length = 100)
    private String correo;

    @Column(length = 20)
    private String telefono;
}
