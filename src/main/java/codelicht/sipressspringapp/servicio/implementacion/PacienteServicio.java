package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Paciente.
 */
@Service
public class PacienteServicio implements IPacienteServicio {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepositorio.findAll();
    }

    @Override
    public Paciente buscarPacientePorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente).orElse(null);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    @Override
    public void eliminarPaciente(Paciente paciente) {
        pacienteRepositorio.delete(paciente);
    }
}
