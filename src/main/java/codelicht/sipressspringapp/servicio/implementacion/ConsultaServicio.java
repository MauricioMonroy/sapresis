package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.repositorio.ConsultaRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementación del servicio para la entidad Consulta.
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

    @Override
    public List<Consulta> buscarConsultasPorFecha(Date fechaConsulta) {
        return consultaRepositorio.findByFechaConsulta(fechaConsulta);
    }

    @Override
    public List<Consulta> buscarConsultasPorHora(Date horaConsulta) {
        return consultaRepositorio.findByHoraConsulta(horaConsulta);
    }
}
