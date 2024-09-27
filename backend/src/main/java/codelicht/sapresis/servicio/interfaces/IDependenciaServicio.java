package codelicht.sapresis.servicio.interfaces;

import codelicht.sapresis.modelo.Dependencia;

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
