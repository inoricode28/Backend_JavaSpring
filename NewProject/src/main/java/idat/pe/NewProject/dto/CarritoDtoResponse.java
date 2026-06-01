package idat.pe.NewProject.dto;
import java.util.Date;
import java.util.List;
public class CarritoDtoResponse {
    private Long idCarrito;
    private Long clienteId;
    private String clienteNombre;
    private List<CarritoDetalleDtoResponse> detalles;
    private Date createdAt;
    public Long getIdCarrito() { return idCarrito; }
    public void setIdCarrito(Long idCarrito) { this.idCarrito = idCarrito; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public List<CarritoDetalleDtoResponse> getDetalles() { return detalles; }
    public void setDetalles(List<CarritoDetalleDtoResponse> detalles) { this.detalles = detalles; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
