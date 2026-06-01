package idat.pe.NewProject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "venta_detalle")
public class VentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENTA_DETALLE")
    private Long idVentaDetalle;

    @ManyToOne
    @JoinColumn(name = "VENTA_ID", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_VARIANTE_ID", nullable = false)
    private ProductoVariante productoVariante;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    public VentaDetalle() {}

    public Long getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Long idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ProductoVariante getProductoVariante() {
        return productoVariante;
    }

    public void setProductoVariante(ProductoVariante productoVariante) {
        this.productoVariante = productoVariante;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
