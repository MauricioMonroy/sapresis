package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.repositorio.ConsultaRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Consulta.
 * Implementa los métodos de la interfaz IConsultaServicio.
 */
@Service
public class ConsultaServicio implements IConsultaServicio {

    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    @Override
    public List<Consulta> listarConsultas() {
        return consultaRepositorio.findAll();
    }

    @Override
    public List<Consulta> buscarConsultasPorIdPaciente(int pacienteId) {
        return consultaRepositorio.findByConsultaPK_PacienteId(pacienteId);
    }

    @Override
    public List<Consulta> buscarConsultasPorIdDoctor(int doctorId) {
        return consultaRepositorio.findByConsultaPK_DoctorId(doctorId);
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

