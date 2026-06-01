package idat.pe.NewProject.dto;
import java.util.Date;
public class NotificacionDtoResponse {
    private Long idNotificacion;
    private Long clienteId;
    private String mensaje;
    private String tipo;
    private Integer leido;
    private Date createdAt;
    public Long getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(Long idNotificacion) { this.idNotificacion = idNotificacion; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getLeido() { return leido; }
    public void setLeido(Integer leido) { this.leido = leido; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
