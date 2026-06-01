package idat.pe.NewProject.service;

import idat.pe.NewProject.dto.CarritoDetalleDtoRequest;
import idat.pe.NewProject.dto.CarritoDtoResponse;

public interface CarritoService {
    CarritoDtoResponse obtenerPorCliente(Long clienteId);
    CarritoDtoResponse agregarProducto(Long clienteId, CarritoDetalleDtoRequest request);
    void eliminarDetalle(Long detalleId);
}
