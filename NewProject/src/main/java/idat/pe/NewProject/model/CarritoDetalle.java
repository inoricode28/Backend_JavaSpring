package idat.pe.NewProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_detalle")
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCARRITO_DETALLE")
    private Long idCarritoDetalle;

    @ManyToOne
    @JoinColumn(name = "CARRITO_ID", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_VARIANTE_ID", nullable = false)
    private ProductoVariante productoVariante;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    public CarritoDetalle() {}

    public Long getIdCarritoDetalle() {
        return idCarritoDetalle;
    }

    public void setIdCarritoDetalle(Long idCarritoDetalle) {
        this.idCarritoDetalle = idCarritoDetalle;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
}
