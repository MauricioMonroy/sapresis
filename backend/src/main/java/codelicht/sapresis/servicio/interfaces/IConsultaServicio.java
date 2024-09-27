package codelicht.sapresis.servicio.interfaces;

import codelicht.sapresis.modelo.Consulta;
import codelicht.sapresis.modelo.ConsultaPK;

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
