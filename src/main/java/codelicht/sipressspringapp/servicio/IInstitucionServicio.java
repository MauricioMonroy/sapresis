package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Institucion;

import java.util.List;

/**
 * Interface para la entidad Institucion.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IInstitucionServicio {
    public List<Institucion> listarInstituciones();

    public Institucion buscarInstitucionPorId(Integer idInstitucion);

    public Institucion guardarInstitucion(Institucion institucion);

    public void eliminarInstitucion(Institucion institucion);
}
