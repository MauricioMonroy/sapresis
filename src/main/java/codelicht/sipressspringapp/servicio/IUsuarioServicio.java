package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Usuario;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Usuario.
 */
public interface IUsuarioServicio {

    /**
     * Lista todos los registros de usuarios.
     *
     * @return una lista de todos los usuarios.
     */
    List<Usuario> listarRegistros();

    /**
     * Busca un registro de usuario por su ID.
     *
     * @param idUsuario el ID del usuario.
     * @return el usuario con el ID especificado, o null si no se encuentra.
     */
    Usuario buscarRegistroPorId(Integer idUsuario);

    /**
     * Guarda un nuevo registro de usuario o actualiza uno existente.
     *
     * @param usuario el usuario a guardar o actualizar.
     * @return el usuario guardado o actualizado.
     */
    Usuario guardarRegistro(Usuario usuario);

    /**
     * Elimina un registro de usuario.
     *
     * @param usuario el usuario a eliminar.
     */
    void eliminarRegistro(Usuario usuario);
}


