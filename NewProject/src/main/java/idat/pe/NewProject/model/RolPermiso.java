package idat.pe.NewProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rol_permiso")
public class RolPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDROL_PERMISO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROL_ID", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "PERMISO_ID", nullable = false)
    private Permiso permiso;

    public RolPermiso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
}
