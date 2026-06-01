package idat.pe.NewProject.dto;
public class JwtDtoResponse {
    private String accessToken;
    private String tokenType;
    private UsuarioDtoResponse usuario;
    public JwtDtoResponse(String accessToken, UsuarioDtoResponse usuario) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.usuario = usuario;
    }
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public UsuarioDtoResponse getUsuario() { return usuario; }
    public void setUsuario(UsuarioDtoResponse usuario) { this.usuario = usuario; }
}
