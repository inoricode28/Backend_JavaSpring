package idat.pe.NewProject.dto;
import java.math.BigDecimal;
public class PedidoDetalleDtoResponse {
    private Long idPedidoDetalle;
    private Long productoVarianteId;
    private String productoNombre;
    private String talla;
    private String color;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    public Long getIdPedidoDetalle() { return idPedidoDetalle; }
    public void setIdPedidoDetalle(Long idPedidoDetalle) { this.idPedidoDetalle = idPedidoDetalle; }
    public Long getProductoVarianteId() { return productoVarianteId; }
    public void setProductoVarianteId(Long productoVarianteId) { this.productoVarianteId = productoVarianteId; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
