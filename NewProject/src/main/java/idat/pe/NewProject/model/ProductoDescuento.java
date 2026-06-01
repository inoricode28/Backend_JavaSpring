package idat.pe.NewProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_descuento")
public class ProductoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUCTO_DESCUENTO")
    private Long idProductoDescuento;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "DESCUENTO_ID", nullable = false)
    private Descuento descuento;

    public ProductoDescuento() {}

    public Long getIdProductoDescuento() {
        return idProductoDescuento;
    }

    public void setIdProductoDescuento(Long idProductoDescuento) {
        this.idProductoDescuento = idProductoDescuento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
}
