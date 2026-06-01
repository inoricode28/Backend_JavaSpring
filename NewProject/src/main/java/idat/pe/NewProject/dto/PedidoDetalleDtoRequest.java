package idat.pe.NewProject.dto;
import java.math.BigDecimal;
public class PedidoDetalleDtoRequest {
    private Long productoVarianteId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    public Long getProductoVarianteId() { return productoVarianteId; }
    public void setProductoVarianteId(Long productoVarianteId) { this.productoVarianteId = productoVarianteId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
