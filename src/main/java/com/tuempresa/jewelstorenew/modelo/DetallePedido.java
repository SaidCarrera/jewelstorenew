package com.tuempresa.jewelstorenew.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class DetallePedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Producto producto;

    private int cantidad;

    @ReadOnly
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    // Asignar automáticamente el precio del producto al seleccionar
    @PrePersist @PreUpdate
    public void asignarPrecioUnitario() {
        if (producto != null && producto.getPrecio() != null) {
            this.precioUnitario = producto.getPrecio();
        }
    }
}
