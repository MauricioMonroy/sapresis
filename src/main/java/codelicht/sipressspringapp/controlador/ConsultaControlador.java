package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultaControlador.class);

    @Autowired
    private IConsultaServicio consultaServicio;

    // http://localhost:8080/sipress-app/consultas
    @GetMapping("/consultas")
    public List<Consulta> obtenerConsultas() {
        return consultaServicio.listarConsultas();
    }

    @GetMapping("/consultas/{pacienteId}/{doctorId}")
    public Consulta buscarConsultaPorId(@PathVariable int pacienteId, @PathVariable int doctorId) {
        ConsultaPK consultaPK = new ConsultaPK();
        consultaPK.setPacienteId(pacienteId);
        consultaPK.setDoctorId(doctorId);
        return consultaServicio.buscarConsultaPorId(consultaPK);
    }

    @PostMapping("/consultas")
    public Consulta agregarConsulta(@RequestBody Consulta consulta) {
        return consultaServicio.guardarConsulta(consulta);
    }

    @DeleteMapping("/consultas/{pacienteId}/{doctorId}")
    public void eliminarConsulta(@PathVariable int pacienteId, @PathVariable int doctorId) {
        ConsultaPK consultaPK = new ConsultaPK();
        consultaPK.setPacienteId(pacienteId);
        consultaPK.setDoctorId(doctorId);
        Consulta consulta = consultaServicio.buscarConsultaPorId(consultaPK);
        if (consulta != null) {
            consultaServicio.eliminarConsulta(consulta);
        }
    }

    @GetMapping("/consultas/fecha/{fechaConsulta}")
    public List<Consulta> buscarConsultasPorFecha(@PathVariable Date fechaConsulta) {
        return consultaServicio.buscarConsultasPorFecha(fechaConsulta);
    }

    @GetMapping("/consultas/hora/{horaConsulta}")
    public List<Consulta> buscarConsultasPorHora(@PathVariable Date horaConsulta) {
        return consultaServicio.buscarConsultasPorHora(horaConsulta);
    }
}
