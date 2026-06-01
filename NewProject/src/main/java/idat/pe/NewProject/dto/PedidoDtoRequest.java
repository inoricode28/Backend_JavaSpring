package idat.pe.NewProject.dto;
import java.util.List;
public class PedidoDtoRequest {
    private String direccion;
    private List<PedidoDetalleDtoRequest> detalles;
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<PedidoDetalleDtoRequest> getDetalles() { return detalles; }
    public void setDetalles(List<PedidoDetalleDtoRequest> detalles) { this.detalles = detalles; }
}
