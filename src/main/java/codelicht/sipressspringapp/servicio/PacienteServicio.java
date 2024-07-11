package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServicio implements IPacienteServicio {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Override
    public List<Paciente> listarRegistros() {
        return pacienteRepositorio.findAll();
    }

    @Override
    public Paciente buscarRegistroPorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente).orElse(null);
    }

    @Override
    public Paciente guardarRegistro(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    @Override
    public void eliminarRegistro(Paciente paciente) {
        pacienteRepositorio.delete(paciente);
    }
}
