package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.repositorio.DependenciaRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Dependencia.
 */
@Service
public class DependenciaServicio implements IDependenciaServicio {

    @Autowired
    private DependenciaRepositorio dependenciaRepositorio;

    @Override
    public List<Dependencia> listarDependencias() {
        return dependenciaRepositorio.findAll();
    }

    @Override
    public Dependencia buscarDependenciaPorId(Integer idDependencia) {
        return dependenciaRepositorio.findById(idDependencia).orElse(null);
    }

    @Override
    public Dependencia guardarDependencia(Dependencia dependencia) {
        return dependenciaRepositorio.save(dependencia);
    }

    @Override
    public void eliminarDependencia(Dependencia dependencia) {
        dependenciaRepositorio.delete(dependencia);
    }
}
