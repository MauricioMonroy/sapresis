package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Personal;

import java.util.List;

/**
 * Interface para la entidad Personal.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IPersonalServicio {
    public List<Personal> listarPersonalS();

    public Personal buscarPersonalPorId(Integer idPersonal);

    public Personal guardarPersonal(Personal personal);

    public void eliminarPersonal(Personal personal);
}
