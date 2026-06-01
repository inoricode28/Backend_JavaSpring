package idat.pe.NewProject.dto;
import java.math.BigDecimal;
public class VentaDetalleDtoResponse {
    private Long idVentaDetalle;
    private Long productoVarianteId;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    public Long getIdVentaDetalle() { return idVentaDetalle; }
    public void setIdVentaDetalle(Long idVentaDetalle) { this.idVentaDetalle = idVentaDetalle; }
    public Long getProductoVarianteId() { return productoVarianteId; }
    public void setProductoVarianteId(Long productoVarianteId) { this.productoVarianteId = productoVarianteId; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
