package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import idat.pe.NewProject.dao.ClienteDao;
import idat.pe.NewProject.dao.UsuarioDao;
import idat.pe.NewProject.dto.ClienteDtoRequest;
import idat.pe.NewProject.dto.ClienteDtoResponse;
import idat.pe.NewProject.model.Cliente;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired private ClienteDao dao;
    @Autowired private UsuarioDao usuarioDao;

    @Override
    public List<ClienteDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ClienteDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
    }

    @Override
    public ClienteDtoResponse crear(ClienteDtoRequest request, Long usuarioId) {
        Cliente c = new Cliente();
        c.setNombre(request.getNombre());
        c.setTelefono(request.getTelefono());
        c.setEmail(request.getEmail());
        c.setDireccion(request.getDireccion());
        c.setEstado(1);
        c.setUsuario(usuarioDao.findById(usuarioId).orElse(null));
        return toDto(dao.save(c));
    }

    @Override
    public ClienteDtoResponse actualizar(Long id, ClienteDtoRequest request) {
        Cliente c = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        c.setNombre(request.getNombre());
        c.setTelefono(request.getTelefono());
        c.setEmail(request.getEmail());
        c.setDireccion(request.getDireccion());
        return toDto(dao.save(c));
    }

    @Override
    public void eliminar(Long id) {
        Cliente c = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        c.setEstado(0);
        dao.save(c);
    }

    private ClienteDtoResponse toDto(Cliente c) {
        ClienteDtoResponse dto = new ClienteDtoResponse();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setTelefono(c.getTelefono());
        dto.setEmail(c.getEmail());
        dto.setDireccion(c.getDireccion());
        dto.setEstado(c.getEstado());
        dto.setUsuarioId(c.getUsuario() != null ? c.getUsuario().getId() : null);
        dto.setCreatedAt(c.getCreatedAt());
        return dto;
    }
}
