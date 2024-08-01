package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Eps;

import java.util.List;

/**
 * Interface para la entidad Eps.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IEpsServicio {
    List<Eps> listarEpsS();

    Eps buscarEpsPorId(Integer idEps);

    Eps guardarEps(Eps eps);

    void eliminarEps(Eps eps);
}
