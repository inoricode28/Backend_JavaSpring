package idat.pe.NewProject.dto;
import java.math.BigDecimal;
import java.util.Date;
public class PagoDtoRequest {
    private Long metodoPagoId;
    private BigDecimal monto;
    private Date fecha;
    private String transaccionExterna;
    public Long getMetodoPagoId() { return metodoPagoId; }
    public void setMetodoPagoId(Long metodoPagoId) { this.metodoPagoId = metodoPagoId; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getTransaccionExterna() { return transaccionExterna; }
    public void setTransaccionExterna(String transaccionExterna) { this.transaccionExterna = transaccionExterna; }
}
