package idat.pe.NewProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.RolDao;
import idat.pe.NewProject.dao.UsuarioDao;
import idat.pe.NewProject.dto.UsuarioDtoResponse;
import idat.pe.NewProject.dto.UsuarioRegistroDtoRequest;
import idat.pe.NewProject.model.Rol;
import idat.pe.NewProject.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncode;

    @Autowired
    private UsuarioDao dao;

    @Autowired
    private RolDao rolDao;

    @Override
    public UsuarioDtoResponse registrar(UsuarioRegistroDtoRequest request) {
        Usuario usu = new Usuario();
        usu.setUsuario(request.getUsuario());
        usu.setPassword(passwordEncode.encode(request.getPassword()));
        usu.setEstado(1);

        if (request.getRolId() != null) {
            Rol rol = rolDao.findById(request.getRolId().longValue()).orElse(null);
            usu.setRol(rol);
        }

        usu = dao.save(usu);

        UsuarioDtoResponse res = new UsuarioDtoResponse();
        res.setId(usu.getId());
        res.setUsuario(usu.getUsuario());
        res.setRol(usu.getRol() != null ? usu.getRol().getNombre() : null);
        res.setEstado(usu.getEstado());
        return res;
    }
}
