package codelicht.sipresswebapp.servicio.implementacion;

import codelicht.sipresswebapp.modelo.Dependencia;
import codelicht.sipresswebapp.repositorio.DependenciaRepositorio;
import codelicht.sipresswebapp.servicio.interfaces.IDependenciaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Dependencia.
 */
@Service
public class DependenciaServicio implements IDependenciaServicio {

    private final DependenciaRepositorio dependenciaRepositorio;

    // Inyección de dependencias por constructor
    public DependenciaServicio(DependenciaRepositorio dependenciaRepositorio) {
        this.dependenciaRepositorio = dependenciaRepositorio;
    }

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
