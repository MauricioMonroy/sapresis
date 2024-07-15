package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    public List<Paciente> listarPacientes();

    public Paciente buscarPacientePorId(Integer idPaciente);

    public Paciente guardarPaciente(Paciente paciente);

    public void eliminarPaciente(Paciente paciente);
}
