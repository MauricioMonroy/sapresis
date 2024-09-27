package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Eps;
import codelicht.sapresis.repositorio.EpsRepositorio;
import codelicht.sapresis.servicio.interfaces.IEpsServicio;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Eps.
 */
@Service
public class EpsServicio implements IEpsServicio {

    private final EpsRepositorio formulaRepositorio;

    // Inyección de dependencias por constructor
    public EpsServicio(EpsRepositorio formulaRepositorio) {
        this.formulaRepositorio = formulaRepositorio;
    }

    @Override
    public List<Eps> listarEpsS() {
        return formulaRepositorio.findAll();
    }

    @Override
    public Eps buscarEpsPorId(Integer idEps) {
        return formulaRepositorio.findById(idEps).orElse(null);
    }

    @Override
    public Eps guardarEps(Eps formula) {
        return formulaRepositorio.save(formula);
    }

    @Override
    public void eliminarEps(Eps formula) {
        formulaRepositorio.delete(formula);
    }
}
