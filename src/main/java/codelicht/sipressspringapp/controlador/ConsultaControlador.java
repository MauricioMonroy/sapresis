package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultaControlador {
    private static final Logger logger = LoggerFactory.getLogger(ConsultaControlador.class);

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
        consultas.forEach(consulta -> logger.info(consulta.toString()));
        return consultas;
    }

    @GetMapping("/consultas/paciente/{pacienteId}")
    public List<Consulta> buscarConsultasPorIdPaciente(@PathVariable int pacienteId) {
        return consultaServicio.buscarConsultasPorIdPaciente(pacienteId);
    }

    @GetMapping("/consultas/doctor/{doctorId}")
    public List<Consulta> buscarConsultasPorIdDoctor(@PathVariable int doctorId) {
        return consultaServicio.buscarConsultasPorIdDoctor(doctorId);
    }

    @PostMapping("/consultas")
    public ResponseEntity<?> agregarConsulta(@Valid @RequestBody Consulta consulta, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Consulta a agregar: {}", consulta);
        if (consulta.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(consulta.getPaciente().getIdPaciente());
            consulta.setPaciente(paciente);
        }
        if (consulta.getDoctor() != null) {
            Doctor doctor = doctorServicio.buscarDoctorPorId(consulta.getDoctor().getIdDoctor());
            consulta.setDoctor(doctor);
        }
        Consulta nuevaConsulta = consultaServicio.guardarConsulta(consulta);
        return ResponseEntity.ok(nuevaConsulta);
    }

    @DeleteMapping("/consultas/{pacienteId}/{doctorId}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable int pacienteId, @PathVariable int doctorId) {
        Consulta consulta = new Consulta();
        consulta.setConsultaPK(new ConsultaPK(pacienteId, doctorId));
        consultaServicio.eliminarConsulta(consulta);
        return ResponseEntity.noContent().build();
    }
}
