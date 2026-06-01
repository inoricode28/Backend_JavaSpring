package idat.pe.NewProject.dto;
import java.util.Date;
public class CategoriaDtoResponse {
    private Long codCategoria;
    private String nombre;
    private Integer estado;
    private Date createdAt;
    public Long getCodCategoria() { return codCategoria; }
    public void setCodCategoria(Long codCategoria) { this.codCategoria = codCategoria; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
