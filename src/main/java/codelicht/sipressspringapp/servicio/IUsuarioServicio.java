package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.UsuarioDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad UsuarioDTO.
 */
public interface IUsuarioServicio {

    /**
     * Obtiene todos los registros Usuario.
     *
     * @return una lista de todos los registros Usuario.
     */
    List<UsuarioDTO> listarRegistros();

    /**
     * Busca un registro Usuario por su ID.
     *
     * @param idUsuarioDTO el ID del registro Usuario.
     * @return el usuario con el ID especificado, o null si no se encuentra.
     */
    UsuarioDTO buscarRegistroPorId(Integer idUsuarioDTO);

    /**
     * Guarda un nuevo registro Usuario o actualiza uno existente.
     *
     * @param usuarioDTO el registro Usuario a guardar o actualizar.
     * @return registro Usuario guardado o actualizado.
     */
    UsuarioDTO guardarRegistro(UsuarioDTO usuarioDTO);

    /**
     * Elimina un registro de Usuario.
     *
     * @param idUsuario el ID del registro Usuario a eliminar.
     */
    void eliminarRegistro(Integer idUsuario);
}


