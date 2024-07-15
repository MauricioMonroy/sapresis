package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Institucion;

import java.util.List;

public interface IInstitucionServicio {
    public List<Institucion> listarInstituciones();

    public Institucion buscarInstitucionPorId(Integer idInstitucion);

    public Institucion guardarInstitucion(Institucion institucion);

    public void eliminarInstitucion(Institucion institucion);
}
