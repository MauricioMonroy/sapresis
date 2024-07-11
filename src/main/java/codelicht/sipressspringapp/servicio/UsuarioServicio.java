package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Usuario.
 */
@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Lista todos los registros de usuarios.
     *
     * @return una lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarRegistros() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Busca un registro de usuario por su ID.
     *
     * @param idUsuario el ID del usuario.
     * @return el usuario con el ID especificado, o null si no se encuentra.
     */
    @Override
    public Usuario buscarRegistroPorId(Integer idUsuario) {
        return usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    /**
     * Guarda un nuevo registro de usuario o actualiza uno existente.
     *
     * @param usuario el usuario a guardar o actualizar.
     * @return el usuario guardado o actualizado.
     */
    @Override
    public Usuario guardarRegistro(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Elimina un registro de usuario.
     *
     * @param usuario el usuario a eliminar.
     */
    @Override
    public void eliminarRegistro(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
}
