package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.CarritoDao;
import idat.pe.NewProject.dao.CarritoDetalleDao;
import idat.pe.NewProject.dao.ClienteDao;
import idat.pe.NewProject.dao.ProductoVarianteDao;
import idat.pe.NewProject.dto.CarritoDetalleDtoRequest;
import idat.pe.NewProject.dto.CarritoDetalleDtoResponse;
import idat.pe.NewProject.dto.CarritoDtoResponse;
import idat.pe.NewProject.model.Carrito;
import idat.pe.NewProject.model.CarritoDetalle;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired private CarritoDao dao;
    @Autowired private CarritoDetalleDao detalleDao;
    @Autowired private ClienteDao clienteDao;
    @Autowired private ProductoVarianteDao varianteDao;

    @Override
    public CarritoDtoResponse obtenerPorCliente(Long clienteId) {
        Carrito carrito = dao.findByCliente_Id(clienteId)
                .orElseGet(() -> {
                    Carrito c = new Carrito();
                    c.setCliente(clienteDao.findById(clienteId)
                            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
                    return dao.save(c);
                });
        return toDto(carrito);
    }

    @Override
    public CarritoDtoResponse agregarProducto(Long clienteId, CarritoDetalleDtoRequest request) {
        Carrito carrito = dao.findByCliente_Id(clienteId)
                .orElseGet(() -> {
                    Carrito c = new Carrito();
                    c.setCliente(clienteDao.findById(clienteId)
                            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
                    return dao.save(c);
                });

        CarritoDetalle detalle = new CarritoDetalle();
        detalle.setCarrito(carrito);
        detalle.setProductoVariante(varianteDao.findById(request.getProductoVarianteId())
                .orElseThrow(() -> new EntityNotFoundException("Variante no encontrada")));
        detalle.setCantidad(request.getCantidad());
        detalleDao.save(detalle);

        return toDto(dao.findById(carrito.getIdCarrito()).orElse(carrito));
    }

    @Override
    public void eliminarDetalle(Long detalleId) {
        detalleDao.deleteById(detalleId);
    }

    private CarritoDtoResponse toDto(Carrito c) {
        CarritoDtoResponse dto = new CarritoDtoResponse();
        dto.setIdCarrito(c.getIdCarrito());
        dto.setClienteId(c.getCliente() != null ? c.getCliente().getId() : null);
        dto.setClienteNombre(c.getCliente() != null ? c.getCliente().getNombre() : null);
        dto.setCreatedAt(c.getCreatedAt());

        List<CarritoDetalle> detalles = detalleDao.findByCarrito_IdCarrito(c.getIdCarrito());
        dto.setDetalles(detalles.stream().map(d -> {
            CarritoDetalleDtoResponse det = new CarritoDetalleDtoResponse();
            det.setIdCarritoDetalle(d.getIdCarritoDetalle());
            det.setProductoVarianteId(d.getProductoVariante() != null ? d.getProductoVariante().getIdVariante() : null);
            det.setProductoNombre(d.getProductoVariante() != null && d.getProductoVariante().getProducto() != null
                    ? d.getProductoVariante().getProducto().getNombre() : null);
            det.setTalla(d.getProductoVariante() != null ? d.getProductoVariante().getTalla() : null);
            det.setColor(d.getProductoVariante() != null ? d.getProductoVariante().getColor() : null);
            det.setPrecio(d.getProductoVariante() != null ? d.getProductoVariante().getPrecio() : null);
            det.setCantidad(d.getCantidad());
            if (d.getProductoVariante() != null && d.getProductoVariante().getPrecio() != null && d.getCantidad() != null) {
                det.setSubtotal(d.getProductoVariante().getPrecio().multiply(java.math.BigDecimal.valueOf(d.getCantidad())));
            }
            return det;
        }).collect(Collectors.toList()));

        return dto;
    }
}
