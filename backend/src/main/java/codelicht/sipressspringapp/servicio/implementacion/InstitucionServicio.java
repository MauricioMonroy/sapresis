package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Institucion;
import codelicht.sipressspringapp.repositorio.InstitucionRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IInstitucionServicio;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Institucion.
 */
@Service
public class InstitucionServicio implements IInstitucionServicio {

    private final InstitucionRepositorio institucionRepositorio;

    // Inyección de dependencias por constructor
    public InstitucionServicio(InstitucionRepositorio institucionRepositorio) {
        this.institucionRepositorio = institucionRepositorio;
    }

    @Override
    public List<Institucion> listarInstituciones() {
        return institucionRepositorio.findAll();
    }

    @Override
    public Institucion buscarInstitucionPorId(Integer idInstitucion) {
        return institucionRepositorio.findById(idInstitucion).orElse(null);
    }

    @Override
    public Institucion guardarInstitucion(Institucion institucion) {
        return institucionRepositorio.save(institucion);
    }

    @Override
    public void eliminarInstitucion(Institucion institucion) {
        institucionRepositorio.delete(institucion);
    }
}
