package idat.pe.NewProject.dto;
import java.math.BigDecimal;
import java.util.Date;
public class DescuentoDtoResponse {
    private Long idDescuento;
    private String nombre;
    private String descripcion;
    private BigDecimal porcentaje;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer estado;
    private Date createdAt;
    public Long getIdDescuento() { return idDescuento; }
    public void setIdDescuento(Long idDescuento) { this.idDescuento = idDescuento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
