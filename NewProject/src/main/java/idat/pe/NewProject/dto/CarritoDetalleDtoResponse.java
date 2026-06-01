package idat.pe.NewProject.dto;
import java.math.BigDecimal;
public class CarritoDetalleDtoResponse {
    private Long idCarritoDetalle;
    private Long productoVarianteId;
    private String productoNombre;
    private String talla;
    private String color;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal subtotal;
    public Long getIdCarritoDetalle() { return idCarritoDetalle; }
    public void setIdCarritoDetalle(Long idCarritoDetalle) { this.idCarritoDetalle = idCarritoDetalle; }
    public Long getProductoVarianteId() { return productoVarianteId; }
    public void setProductoVarianteId(Long productoVarianteId) { this.productoVarianteId = productoVarianteId; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
