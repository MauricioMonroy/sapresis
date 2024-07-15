package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Paciente;

import java.util.List;

/**
 * Interface para la entidad Paciente.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IPacienteServicio {
    public List<Paciente> listarPacientes();

    public Paciente buscarPacientePorId(Integer idPaciente);

    public Paciente guardarPaciente(Paciente paciente);

    public void eliminarPaciente(Paciente paciente);
}
