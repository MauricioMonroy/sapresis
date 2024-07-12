package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.modelo.Historial;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Historial.
 */
public interface IHistorialServicio {

    /**
     * Lista todos los registros de historiales.
     *
     * @return una lista de todos los historiales.
     */
    List<HistorialDTO> listarRegistros();

    /**
     * Busca un registro de historial por su ID.
     *
     * @param idHistorial el ID del historial.
     * @return el historial con el ID especificado, o null si no se encuentra.
     */
    HistorialDTO buscarRegistroPorId(Integer idHistorial);

    /**
     * Guarda un nuevo registro de historial o actualiza uno existente.
     *
     * @param historialDTO el historial a guardar o actualizar.
     * @return el historial guardado o actualizado.
     */
    HistorialDTO guardarRegistro(HistorialDTO historialDTO);

    /**
     * Elimina un registro de historial.
     *
     * @param idHistorial el historial a eliminar.
     */
    void eliminarRegistro(Integer idHistorial);
}

