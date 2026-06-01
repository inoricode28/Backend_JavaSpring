package idat.pe.NewProject.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDNOTIFICACION")
    private Long idNotificacion;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;

    @Column(name = "MENSAJE", columnDefinition = "TEXT", nullable = false)
    private String mensaje;

    @Column(name = "TIPO", length = 50)
    private String tipo;

    @Column(name = "LEIDO")
    private Integer leido = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDAT", nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Notificacion() {}

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getLeido() {
        return leido;
    }

    public void setLeido(Integer leido) {
        this.leido = leido;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
