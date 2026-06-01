package idat.pe.NewProject.dto;
import java.math.BigDecimal;
import java.util.Date;
public class PagoDtoResponse {
    private Long idPago;
    private Long ventaId;
    private Long metodoPagoId;
    private String metodoPagoNombre;
    private BigDecimal monto;
    private Date fecha;
    private Integer estado;
    private String transaccionExterna;
    public Long getIdPago() { return idPago; }
    public void setIdPago(Long idPago) { this.idPago = idPago; }
    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }
    public Long getMetodoPagoId() { return metodoPagoId; }
    public void setMetodoPagoId(Long metodoPagoId) { this.metodoPagoId = metodoPagoId; }
    public String getMetodoPagoNombre() { return metodoPagoNombre; }
    public void setMetodoPagoNombre(String metodoPagoNombre) { this.metodoPagoNombre = metodoPagoNombre; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public String getTransaccionExterna() { return transaccionExterna; }
    public void setTransaccionExterna(String transaccionExterna) { this.transaccionExterna = transaccionExterna; }
}
