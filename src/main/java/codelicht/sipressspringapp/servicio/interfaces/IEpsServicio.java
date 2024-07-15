package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Eps;

import java.util.List;

/**
 * Interface para la entidad Eps.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IEpsServicio {
    public List<Eps> listarEpsS();

    public Eps buscarEpsPorId(Integer idEps);

    public Eps guardarEps(Eps eps);

    public void eliminarEps(Eps eps);
}
