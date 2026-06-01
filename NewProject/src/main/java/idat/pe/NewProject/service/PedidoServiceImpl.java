package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idat.pe.NewProject.dao.ClienteDao;
import idat.pe.NewProject.dao.PedidoDao;
import idat.pe.NewProject.dao.PedidoDetalleDao;
import idat.pe.NewProject.dao.ProductoVarianteDao;
import idat.pe.NewProject.dto.PedidoDetalleDtoResponse;
import idat.pe.NewProject.dto.PedidoDtoRequest;
import idat.pe.NewProject.dto.PedidoDtoResponse;
import idat.pe.NewProject.model.Pedido;
import idat.pe.NewProject.model.PedidoDetalle;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired private PedidoDao dao;
    @Autowired private PedidoDetalleDao detalleDao;
    @Autowired private ClienteDao clienteDao;
    @Autowired private ProductoVarianteDao varianteDao;

    @Override
    public List<PedidoDtoResponse> listarPorCliente(Long clienteId) {
        return dao.findByCliente_Id(clienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PedidoDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado")));
    }

    @Override
    @Transactional
    public PedidoDtoResponse crear(Long clienteId, PedidoDtoRequest request) {
        Pedido p = new Pedido();
        p.setCliente(clienteDao.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
        p.setDireccion(request.getDireccion());
        p.setEstado(1);

        Pedido saved = dao.save(p);

        if (request.getDetalles() != null) {
            for (var dto : request.getDetalles()) {
                PedidoDetalle det = new PedidoDetalle();
                det.setPedido(saved);
                det.setProductoVariante(varianteDao.findById(dto.getProductoVarianteId())
                        .orElseThrow(() -> new EntityNotFoundException("Variante no encontrada")));
                det.setCantidad(dto.getCantidad());
                det.setPrecioUnitario(dto.getPrecioUnitario());
                detalleDao.save(det);
            }
        }

        return toDto(dao.findById(saved.getIdPedido()).orElse(saved));
    }

    @Override
    public PedidoDtoResponse actualizarEstado(Long id, Integer estado) {
        Pedido p = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        p.setEstado(estado);
        return toDto(dao.save(p));
    }

    private PedidoDtoResponse toDto(Pedido p) {
        PedidoDtoResponse dto = new PedidoDtoResponse();
        dto.setIdPedido(p.getIdPedido());
        dto.setClienteId(p.getCliente() != null ? p.getCliente().getId() : null);
        dto.setClienteNombre(p.getCliente() != null ? p.getCliente().getNombre() : null);
        dto.setDireccion(p.getDireccion());
        dto.setEstado(p.getEstado());
        dto.setFechaEntrega(p.getFechaEntrega());
        dto.setCreatedAt(p.getCreatedAt());

        List<PedidoDetalle> detalles = detalleDao.findByPedido_IdPedido(p.getIdPedido());
        dto.setDetalles(detalles.stream().map(d -> {
            PedidoDetalleDtoResponse det = new PedidoDetalleDtoResponse();
            det.setIdPedidoDetalle(d.getIdPedidoDetalle());
            det.setProductoVarianteId(d.getProductoVariante() != null ? d.getProductoVariante().getIdVariante() : null);
            det.setProductoNombre(d.getProductoVariante() != null && d.getProductoVariante().getProducto() != null
                    ? d.getProductoVariante().getProducto().getNombre() : null);
            det.setTalla(d.getProductoVariante() != null ? d.getProductoVariante().getTalla() : null);
            det.setColor(d.getProductoVariante() != null ? d.getProductoVariante().getColor() : null);
            det.setCantidad(d.getCantidad());
            det.setPrecioUnitario(d.getPrecioUnitario());
            return det;
        }).collect(Collectors.toList()));

        return dto;
    }
}
