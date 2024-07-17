package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;

import java.util.Date;
import java.util.List;

/**
 * Interface para la entidad Consulta.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IConsultaServicio {
    public List<Consulta> listarConsultas();

    public Consulta buscarConsultaPorId(ConsultaPK consultaPK);

    public Consulta guardarConsulta(Consulta consulta);

    public void eliminarConsulta(Consulta consulta);

}
