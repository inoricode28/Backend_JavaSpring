package idat.pe.NewProject.dto;
import java.util.Date;
public class ResenaDtoResponse {
    private Long idResena;
    private Long productoId;
    private Long clienteId;
    private String clienteNombre;
    private Integer calificacion;
    private String comentario;
    private Date createdAt;
    public Long getIdResena() { return idResena; }
    public void setIdResena(Long idResena) { this.idResena = idResena; }
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
