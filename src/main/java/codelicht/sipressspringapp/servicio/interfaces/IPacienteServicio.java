package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Paciente;

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
