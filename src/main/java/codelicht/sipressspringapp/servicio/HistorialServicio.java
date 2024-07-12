package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Historial;
import codelicht.sipressspringapp.repositorio.HistorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio para la entidad Historial.
 */
@Service
public class HistorialServicio implements IHistorialServicio {

    @Autowired
    private HistorialRepositorio historialRepositorio;

    /**
     * Lista todos los registros de historiales.
     *
     * @return una lista de todos los historiales.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Historial> listarRegistros() {
        return historialRepositorio.findAll();
    }

    /**
     * Busca un registro de historial por su ID.
     *
     * @param idHistorial el ID del historial.
     * @return el historial con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Historial buscarRegistroPorId(Integer idHistorial) {
        return historialRepositorio.findById(idHistorial).orElse(null);
    }

    /**
     * Guarda un nuevo registro de historial o actualiza uno existente.
     *
     * @param historial el historial a guardar o actualizar.
     * @return el historial guardado o actualizado.
     */
    @Override
    @Transactional
    public Historial guardarRegistro(Historial historial) {
        return historialRepositorio.save(historial);
    }

    /**
     * Elimina un registro de historial.
     *
     * @param historial el historial a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Historial historial) {
        historialRepositorio.delete(historial);
    }
}
