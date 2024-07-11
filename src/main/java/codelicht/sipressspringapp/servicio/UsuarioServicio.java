package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> listarRegistros() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario buscarRegistroPorId(Integer idUsuario) {
        return usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario guardarRegistro(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarRegistro(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
}
