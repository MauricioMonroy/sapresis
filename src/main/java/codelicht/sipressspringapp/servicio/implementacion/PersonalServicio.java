package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Personal;
import codelicht.sipressspringapp.repositorio.PersonalRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IPersonalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Personal.
 */
@Service
public class PersonalServicio implements IPersonalServicio {

    @Autowired
    private PersonalRepositorio personalRepositorio;

    @Override
    public List<Personal> listarPersonalS() {
        return personalRepositorio.findAll();
    }

    @Override
    public Personal buscarPersonalPorId(Integer idPersonal) {
        return personalRepositorio.findById(idPersonal).orElse(null);
    }

    @Override
    public Personal guardarPersonal(Personal personal) {
        return personalRepositorio.save(personal);
    }

    @Override
    public void eliminarPersonal(Personal personal) {
        personalRepositorio.delete(personal);
    }
}
