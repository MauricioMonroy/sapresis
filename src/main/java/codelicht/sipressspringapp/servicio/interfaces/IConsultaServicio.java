package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;

import java.util.List;

/**
 * Interface para la entidad Consulta.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IConsultaServicio {

    List<Consulta> listarConsultas();

    List<Consulta> buscarConsultaPorIdPaciente(Integer idPaciente);

    List<Consulta> buscarConsultaPorIdDoctor(Integer idDoctor);

    Consulta buscarConsultaPorId(ConsultaPK consultaPK);

    Consulta guardarConsulta(Consulta consulta);

    void eliminarConsulta(Consulta consulta);
}
