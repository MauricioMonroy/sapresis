package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    List<Paciente> listarRegistros();

    Paciente buscarRegistroPorId(Integer idPaciente);

    Paciente guardarRegistro(Paciente paciente);

    void eliminarRegistro(Paciente paciente);
}

