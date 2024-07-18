package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Consulta;

import java.util.List;

/**
 * Interface para la entidad Consulta.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IConsultaServicio {

    List<Consulta> listarConsultas();

    List<Consulta> buscarConsultasPorIdPaciente(int pacienteId);

    List<Consulta> buscarConsultasPorIdDoctor(int doctorId);

    Consulta guardarConsulta(Consulta consulta);

    void eliminarConsulta(Consulta consulta);
}

