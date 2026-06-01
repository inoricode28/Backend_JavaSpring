package idat.pe.NewProject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import idat.pe.NewProject.model.Usuario;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String usuario;
    private String rolNombre;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String usuario, String rolNombre, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.rolNombre = rolNombre;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        String roleName = "ROLE_" + (usuario.getRol() != null ? usuario.getRol().getNombre().toUpperCase() : "CLIENTE");
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return new UserDetailsImpl(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getRol() != null ? usuario.getRol().getNombre() : null,
                usuario.getPassword(),
                authorities);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getRolNombre() { return rolNombre; }
    public void setRolNombre(String rolNombre) { this.rolNombre = rolNombre; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return usuario; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
