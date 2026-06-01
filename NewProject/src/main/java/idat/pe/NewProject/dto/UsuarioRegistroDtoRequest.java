package idat.pe.NewProject.dto;
public class UsuarioRegistroDtoRequest {
    private String usuario;
    private String password;
    private Integer rolId;
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Integer getRolId() { return rolId; }
    public void setRolId(Integer rolId) { this.rolId = rolId; }
}
