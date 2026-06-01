package idat.pe.NewProject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto_variante")
public class ProductoVariante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVARIANTE")
    private Long idVariante;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;

    @Column(name = "TALLA", length = 50)
    private String talla;

    @Column(name = "COLOR", length = 50)
    private String color;

    @Column(name = "PRECIO", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "STOCK")
    private Integer stock;

    public ProductoVariante() {}

    public ProductoVariante(Long idVariante) {
        this.idVariante = idVariante;
    }

    public Long getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(Long idVariante) {
        this.idVariante = idVariante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
