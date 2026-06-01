package idat.pe.NewProject.dto;
public class CarritoDetalleDtoRequest {
    private Long productoVarianteId;
    private Integer cantidad;
    public Long getProductoVarianteId() { return productoVarianteId; }
    public void setProductoVarianteId(Long productoVarianteId) { this.productoVarianteId = productoVarianteId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
