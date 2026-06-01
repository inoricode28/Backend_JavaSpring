package idat.pe.NewProject.dto;
import java.math.BigDecimal;
public class ProductoVarianteDtoRequest {
    private String talla;
    private String color;
    private BigDecimal precio;
    private Integer stock;
    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
