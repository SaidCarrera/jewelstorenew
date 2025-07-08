package com.tuempresa.jewelstorenew.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Required
    private Date fecha;

    @Column(length = 20)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Cliente cliente;

    @ReadOnly @Depends("detalles")
    private BigDecimal subtotal;

    @ReadOnly @Depends("detalles")
    private BigDecimal iva;

    @ReadOnly @Depends("detalles")
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @ListProperties("producto.nombre, cantidad, precioUnitario")
    private Collection<DetallePedido> detalles;

    // Recalcular totales antes de guardar
    @PrePersist @PreUpdate
    public void calcularTotales() {
        if (detalles == null || detalles.isEmpty()) {
            this.subtotal = BigDecimal.ZERO;
            this.iva = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            return;
        }

        BigDecimal suma = BigDecimal.ZERO;

        for (DetallePedido detalle : detalles) {
            if (detalle.getPrecioUnitario() != null && detalle.getCantidad() > 0) {
                suma = suma.add(detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad())));
            }
        }

        this.subtotal = suma;
        this.iva = suma.multiply(new BigDecimal("0.15"));
        this.total = suma.add(this.iva);
    }
}
