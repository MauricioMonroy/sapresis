package codelicht.sipresswebapp.servicio.interfaces;

import codelicht.sipresswebapp.modelo.Institucion;

import java.util.List;

/**
 * Interface para la entidad Institucion.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IInstitucionServicio {
    List<Institucion> listarInstituciones();

    Institucion buscarInstitucionPorId(Integer idInstitucion);

    Institucion guardarInstitucion(Institucion institucion);

    void eliminarInstitucion(Institucion institucion);
}
