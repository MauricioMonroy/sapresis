package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.repositorio.ConsultaRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Consulta.
 * Implementa los métodos de la interfaz IConsultaServicio.
 */
@Service
public class ConsultaServicio implements IConsultaServicio {

    private final ConsultaRepositorio consultaRepositorio;

    // Inyección de dependencias por constructor
    public ConsultaServicio(ConsultaRepositorio consultaRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
    }

    @Override
    public List<Consulta> listarConsultas() {
        return consultaRepositorio.findAll();
    }

    @Override
    public List<Consulta> buscarConsultaPorIdPaciente(Integer idPaciente) {
        return consultaRepositorio.findByConsultaPK_PacienteId(idPaciente);
    }

    @Override
    public List<Consulta> buscarConsultaPorIdDoctor(Integer idDoctor) {
        return consultaRepositorio.findByConsultaPK_DoctorId(idDoctor);
    }

    @Override
    public Consulta buscarConsultaPorId(ConsultaPK consultaPK) {
        return consultaRepositorio.findById(consultaPK).orElse(null);
    }

    @Override
    public Consulta guardarConsulta(Consulta consulta) {
        return consultaRepositorio.save(consulta);
    }

    @Override
    public void eliminarConsulta(Consulta consulta) {
        consultaRepositorio.delete(consulta);
    }
}

