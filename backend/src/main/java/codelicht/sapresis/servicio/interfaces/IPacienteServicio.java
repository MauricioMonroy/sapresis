package codelicht.sapresis.servicio.interfaces;

import codelicht.sapresis.modelo.Paciente;

import java.util.List;

/**
 * Interface para la entidad Paciente.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IPacienteServicio {
    List<Paciente> listarPacientes();

    Paciente buscarPacientePorId(Integer idPaciente);

    Paciente guardarPaciente(Paciente paciente);

    void eliminarPaciente(Paciente paciente);
}
