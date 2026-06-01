package idat.pe.NewProject.dto;
import java.util.Date;
import java.util.List;
public class PedidoDtoResponse {
    private Long idPedido;
    private Long clienteId;
    private String clienteNombre;
    private String direccion;
    private Integer estado;
    private Date fechaEntrega;
    private Date createdAt;
    private List<PedidoDetalleDtoResponse> detalles;
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Date fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public List<PedidoDetalleDtoResponse> getDetalles() { return detalles; }
    public void setDetalles(List<PedidoDetalleDtoResponse> detalles) { this.detalles = detalles; }
}
