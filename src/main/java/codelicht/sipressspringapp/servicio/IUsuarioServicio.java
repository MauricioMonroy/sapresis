package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.UsuarioDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad UsuarioDTO.
 */
public interface IUsuarioServicio {

    /**
     * Lista todos los registros de usuarioDTOs.
     *
     * @return una lista de todos los usuarioDTOs.
     */
    List<UsuarioDTO> listarRegistros();

    /**
     * Busca un registro de usuarioDTO por su ID.
     *
     * @param idUsuarioDTO el ID del usuarioDTO.
     * @return el usuarioDTO con el ID especificado, o null si no se encuentra.
     */
    UsuarioDTO buscarRegistroPorId(Integer idUsuarioDTO);

    /**
     * Guarda un nuevo registro de usuarioDTO o actualiza uno existente.
     *
     * @param usuarioDTO el usuarioDTO a guardar o actualizar.
     * @return el usuarioDTO guardado o actualizado.
     */
    UsuarioDTO guardarRegistro(UsuarioDTO usuarioDTO);

    /**
     * Elimina un registro de usuarioDTO.
     *
     * @param idUsuario el usuarioDTO a eliminar.
     */
    void eliminarRegistro(Integer idUsuario);
}


