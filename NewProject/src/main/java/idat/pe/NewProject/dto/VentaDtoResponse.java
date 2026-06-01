package idat.pe.NewProject.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class VentaDtoResponse {
    private Long idVenta;
    private Long clienteId;
    private String clienteNombre;
    private Long usuarioId;
    private Long pedidoId;
    private String codigo;
    private BigDecimal precioTotal;
    private Integer estado;
    private List<VentaDetalleDtoResponse> detalles;
    private Date createdAt;
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public List<VentaDetalleDtoResponse> getDetalles() { return detalles; }
    public void setDetalles(List<VentaDetalleDtoResponse> detalles) { this.detalles = detalles; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
