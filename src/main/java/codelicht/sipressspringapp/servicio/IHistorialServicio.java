package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.HistorialDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Historial.
 */
public interface IHistorialServicio {

    /**
     * Lista todos los registros de Historial.
     *
     * @return una lista de todos los registros Historial.
     */
    List<HistorialDTO> listarRegistros();

    /**
     * Busca un registro Historial por su ID.
     *
     * @param idHistorialDTO el ID del registro Historial.
     * @return el historial con el ID especificado, o null si no se encuentra.
     */
    HistorialDTO buscarRegistroPorId(Integer idHistorialDTO);

    /**
     * Guarda un nuevo registro Historial o actualiza uno existente.
     *
     * @param historialDTO el registro Historial a guardar o actualizar.
     * @return registro Historial guardado o actualizado.
     */
    HistorialDTO guardarRegistro(HistorialDTO historialDTO);

    /**
     * Elimina un registro de Historial.
     *
     * @param idHistorial el ID del registro Historial a eliminar.
     */
    void eliminarRegistro(Integer idHistorial);
}

