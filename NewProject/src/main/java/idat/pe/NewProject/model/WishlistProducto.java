package idat.pe.NewProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist_producto")
public class WishlistProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDWISHLIST_PRODUCTO")
    private Long idWishlistProducto;

    @ManyToOne
    @JoinColumn(name = "WISHLIST_ID", nullable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_VARIANTE_ID", nullable = false)
    private ProductoVariante productoVariante;

    public WishlistProducto() {}

    public Long getIdWishlistProducto() {
        return idWishlistProducto;
    }

    public void setIdWishlistProducto(Long idWishlistProducto) {
        this.idWishlistProducto = idWishlistProducto;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public ProductoVariante getProductoVariante() {
        return productoVariante;
    }

    public void setProductoVariante(ProductoVariante productoVariante) {
        this.productoVariante = productoVariante;
    }
}
