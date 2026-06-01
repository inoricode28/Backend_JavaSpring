package idat.pe.NewProject.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idat.pe.NewProject.dao.ClienteDao;
import idat.pe.NewProject.dao.PedidoDao;
import idat.pe.NewProject.dao.PedidoDetalleDao;
import idat.pe.NewProject.dao.UsuarioDao;
import idat.pe.NewProject.dao.VentaDao;
import idat.pe.NewProject.dao.VentaDetalleDao;
import idat.pe.NewProject.dto.VentaDetalleDtoResponse;
import idat.pe.NewProject.dto.VentaDtoResponse;
import idat.pe.NewProject.model.Venta;
import idat.pe.NewProject.model.VentaDetalle;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired private VentaDao dao;
    @Autowired private VentaDetalleDao detalleDao;
    @Autowired private ClienteDao clienteDao;
    @Autowired private UsuarioDao usuarioDao;
    @Autowired private PedidoDao pedidoDao;
    @Autowired private PedidoDetalleDao pedidoDetalleDao;

    @Override
    public List<VentaDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VentaDtoResponse> listarPorCliente(Long clienteId) {
        return dao.findByCliente_Id(clienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public VentaDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Venta no encontrada")));
    }

    @Override
    @Transactional
    public VentaDtoResponse crear(Long clienteId, Long usuarioId, Long pedidoId) {
        var pedido = pedidoDao.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        Venta v = new Venta();
        v.setCliente(clienteDao.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
        v.setUsuario(usuarioDao.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));
        v.setPedido(pedido);
        v.setCodigo("V-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        v.setEstado(1);

        BigDecimal total = BigDecimal.ZERO;
        var detallesPedido = pedidoDetalleDao.findByPedido_IdPedido(pedidoId);
        for (var dp : detallesPedido) {
            if (dp.getPrecioUnitario() != null) {
                total = total.add(dp.getPrecioUnitario().multiply(BigDecimal.valueOf(dp.getCantidad())));
            }
        }
        v.setPrecioTotal(total);

        Venta saved = dao.save(v);

        for (var dp : detallesPedido) {
            VentaDetalle vd = new VentaDetalle();
            vd.setVenta(saved);
            vd.setProductoVariante(dp.getProductoVariante());
            vd.setCantidad(dp.getCantidad());
            vd.setPrecioUnitario(dp.getPrecioUnitario());
            detalleDao.save(vd);
        }

        return toDto(dao.findById(saved.getIdVenta()).orElse(saved));
    }

    @Override
    public void actualizarEstado(Long id, Integer estado) {
        Venta v = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));
        v.setEstado(estado);
        dao.save(v);
    }

    private VentaDtoResponse toDto(Venta v) {
        VentaDtoResponse dto = new VentaDtoResponse();
        dto.setIdVenta(v.getIdVenta());
        dto.setClienteId(v.getCliente() != null ? v.getCliente().getId() : null);
        dto.setClienteNombre(v.getCliente() != null ? v.getCliente().getNombre() : null);
        dto.setUsuarioId(v.getUsuario() != null ? v.getUsuario().getId() : null);
        dto.setPedidoId(v.getPedido() != null ? v.getPedido().getIdPedido() : null);
        dto.setCodigo(v.getCodigo());
        dto.setPrecioTotal(v.getPrecioTotal());
        dto.setEstado(v.getEstado());
        dto.setCreatedAt(v.getCreatedAt());

        List<VentaDetalle> detalles = detalleDao.findByVenta_IdVenta(v.getIdVenta());
        dto.setDetalles(detalles.stream().map(d -> {
            VentaDetalleDtoResponse det = new VentaDetalleDtoResponse();
            det.setIdVentaDetalle(d.getIdVentaDetalle());
            det.setProductoVarianteId(d.getProductoVariante() != null ? d.getProductoVariante().getIdVariante() : null);
            det.setProductoNombre(d.getProductoVariante() != null && d.getProductoVariante().getProducto() != null
                    ? d.getProductoVariante().getProducto().getNombre() : null);
            det.setCantidad(d.getCantidad());
            det.setPrecioUnitario(d.getPrecioUnitario());
            return det;
        }).collect(Collectors.toList()));

        return dto;
    }
}
