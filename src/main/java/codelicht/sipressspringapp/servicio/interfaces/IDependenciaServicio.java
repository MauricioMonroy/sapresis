package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Dependencia;

import java.util.List;

/**
 * Interface para la entidad Dependencia.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IDependenciaServicio {
    List<Dependencia> listarDependencias();

    Dependencia buscarDependenciaPorId(Integer idDependencia);

    Dependencia guardarDependencia(Dependencia dependencia);

    void eliminarDependencia(Dependencia dependencia);
}
