package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.MetodoPagoDao;
import idat.pe.NewProject.dao.PagoDao;
import idat.pe.NewProject.dao.VentaDao;
import idat.pe.NewProject.dto.PagoDtoRequest;
import idat.pe.NewProject.dto.PagoDtoResponse;
import idat.pe.NewProject.model.Pago;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired private PagoDao dao;
    @Autowired private VentaDao ventaDao;
    @Autowired private MetodoPagoDao metodoPagoDao;

    @Override
    public List<PagoDtoResponse> listarPorVenta(Long ventaId) {
        return dao.findByVenta_IdVenta(ventaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PagoDtoResponse crearPago(Long ventaId, PagoDtoRequest request) {
        Pago p = new Pago();
        p.setVenta(ventaDao.findById(ventaId)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada")));
        p.setMetodoPago(metodoPagoDao.findById(request.getMetodoPagoId())
                .orElseThrow(() -> new EntityNotFoundException("Metodo de pago no encontrado")));
        p.setMonto(request.getMonto());
        p.setFecha(request.getFecha());
        p.setEstado(1);
        p.setTransaccionExterna(request.getTransaccionExterna());
        return toDto(dao.save(p));
    }

    private PagoDtoResponse toDto(Pago p) {
        PagoDtoResponse dto = new PagoDtoResponse();
        dto.setIdPago(p.getIdPago());
        dto.setVentaId(p.getVenta() != null ? p.getVenta().getIdVenta() : null);
        dto.setMetodoPagoId(p.getMetodoPago() != null ? p.getMetodoPago().getIdMetodoPago() : null);
        dto.setMetodoPagoNombre(p.getMetodoPago() != null ? p.getMetodoPago().getNombre() : null);
        dto.setMonto(p.getMonto());
        dto.setFecha(p.getFecha());
        dto.setEstado(p.getEstado());
        dto.setTransaccionExterna(p.getTransaccionExterna());
        return dto;
    }
}
