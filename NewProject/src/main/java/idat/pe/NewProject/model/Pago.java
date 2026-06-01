package idat.pe.NewProject.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPAGO")
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "VENTA_ID", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "METODO_PAGO_ID", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "MONTO", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;

    @Column(name = "TRANSACCION_EXTERNA", length = 255)
    private String transaccionExterna;

    public Pago() {}

    public Pago(Long idPago) {
        this.idPago = idPago;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTransaccionExterna() {
        return transaccionExterna;
    }

    public void setTransaccionExterna(String transaccionExterna) {
        this.transaccionExterna = transaccionExterna;
    }
}
