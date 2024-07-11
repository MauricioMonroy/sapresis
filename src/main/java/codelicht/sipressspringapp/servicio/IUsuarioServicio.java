package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {
    List<Usuario> listarRegistros();

    Usuario buscarRegistroPorId(Integer idUsuario);

    Usuario guardarRegistro(Usuario usuario);

    void eliminarRegistro(Usuario usuario);
}

