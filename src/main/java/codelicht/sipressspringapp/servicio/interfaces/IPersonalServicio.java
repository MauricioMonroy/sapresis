package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Personal;

import java.util.List;

/**
 * Interface para la entidad Personal.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IPersonalServicio {
    List<Personal> listarPersonalS();

    Personal buscarPersonalPorId(Integer idPersonal);

    Personal guardarPersonal(Personal personal);

    void eliminarPersonal(Personal personal);
}
