package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultaControlador.class);

    @Autowired
    private IConsultaServicio consultaServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    @Autowired
    private IDoctorServicio doctorServicio;

    // http://localhost:8080/sipress-app/consultas
    @GetMapping("/consultas")
    public List<Consulta> obtenerConsultas() {
        var consultas = consultaServicio.listarConsultas();
        consultas.forEach((consulta -> logger.info(consulta.toString())));
        return consultas;
    }

    @GetMapping("/consultas/{pacienteId}")
    public Consulta buscarConsultaPorIdPaciente(@PathVariable int pacienteId) {
        ConsultaPK consultaPK = new ConsultaPK();
        consultaPK.setPacienteId(pacienteId);
        return consultaServicio.buscarConsultaPorId(consultaPK);
    }

    @GetMapping("/consultas/{doctorId}")
    public Consulta buscarConsultaPorIdDoctor(@PathVariable int doctorId) {
        ConsultaPK consultaPK = new ConsultaPK();
        consultaPK.setDoctorId(doctorId);
        return consultaServicio.buscarConsultaPorId(consultaPK);
    }

    @PostMapping("/consultas")
    public Consulta agregarConsulta(@RequestBody Consulta consulta) {
        logger.info("Consulta a agregar: {}", consulta);
        if (consulta.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(consulta.getPaciente().getIdPaciente());
            consulta.setPaciente(paciente);
        }
        if (consulta.getDoctor() != null) {
            Doctor doctor = doctorServicio.buscarDoctorPorId(consulta.getDoctor().getIdDoctor());
            consulta.setDoctor(doctor);
        }
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
}
