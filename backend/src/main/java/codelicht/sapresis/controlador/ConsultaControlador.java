package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Consulta;
import codelicht.sapresis.modelo.ConsultaPK;
import codelicht.sapresis.modelo.Doctor;
import codelicht.sapresis.modelo.Paciente;
import codelicht.sapresis.servicio.interfaces.IConsultaServicio;
import codelicht.sapresis.servicio.interfaces.IDoctorServicio;
import codelicht.sapresis.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultaControlador {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaControlador.class);

    private final IConsultaServicio consultaServicio;
    private final IPacienteServicio pacienteServicio;
    private final IDoctorServicio doctorServicio;

    public ConsultaControlador(IConsultaServicio consultaServicio, IPacienteServicio pacienteServicio, IDoctorServicio doctorServicio) {
        this.consultaServicio = consultaServicio;
        this.pacienteServicio = pacienteServicio;
        this.doctorServicio = doctorServicio;
    }

    @GetMapping("/consultas")
    public List<Consulta> obtenerConsultas() {
        var consultas = consultaServicio.listarConsultas();
        consultas.forEach(consulta -> logger.info(consulta.toString()));
        return consultas;
    }

    @GetMapping("/consultas/{pacienteId}/{doctorId}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable Integer pacienteId, @PathVariable Integer doctorId) {
        ConsultaPK consultaPK = new ConsultaPK(pacienteId, doctorId);
        Consulta consulta = consultaServicio.buscarConsultaPorId(consultaPK);
        if (consulta == null) {
            throw new RecursoNoEncontradoExcepcion("Consulta no encontrada con el id de paciente: " + pacienteId + " y id de doctor: " + doctorId);
        }
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/consultas/paciente/{id}")
    public ResponseEntity<List<Consulta>> buscarConsultaPorIdPaciente(@PathVariable("id") Integer idPaciente) {
        List<Consulta> consultas = consultaServicio.buscarConsultaPorIdPaciente(idPaciente);
        if (consultas == null || consultas.isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("Consulta no encontrada con el id de paciente: " + idPaciente);
        }
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/consultas/doctor/{id}")
    public ResponseEntity<List<Consulta>> buscarConsultaPorIdDoctor(@PathVariable("id") Integer idDoctor) {
        List<Consulta> consultas = consultaServicio.buscarConsultaPorIdDoctor(idDoctor);
        if (consultas == null || consultas.isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("Consulta no encontrada con el id de doctor: " + idDoctor);
        }
        return ResponseEntity.ok(consultas);
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
            if (paciente == null) {
                throw new RecursoNoEncontradoExcepcion("Paciente no encontrado con el id: " + consulta.getPaciente().getIdPaciente());
            }
            consulta.setPaciente(paciente);
        }

        if (consulta.getDoctor() != null) {
            Doctor doctor = doctorServicio.buscarDoctorPorId(consulta.getDoctor().getIdDoctor());
            if (doctor == null) {
                throw new RecursoNoEncontradoExcepcion("Doctor no encontrado con el id: " + consulta.getDoctor().getIdDoctor());
            }
            consulta.setDoctor(doctor);
        }

        assert consulta.getPaciente() != null;
        assert consulta.getDoctor() != null;
        consulta.setConsultaPK(new ConsultaPK(consulta.getPaciente().getIdPaciente(), consulta.getDoctor().getIdDoctor()));

        Consulta nuevaConsulta = consultaServicio.guardarConsulta(consulta);
        return ResponseEntity.ok(nuevaConsulta);
    }

    @PutMapping("/consultas/{pacienteId}/{doctorId}")
    public ResponseEntity<Consulta> actualizarConsulta(@PathVariable Integer pacienteId,
                                                       @PathVariable Integer doctorId,
                                                       @Valid @RequestBody Consulta consultaRecuperada,
                                                       BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body((Consulta) errors);
        }

        ConsultaPK consultaPK = new ConsultaPK(pacienteId, doctorId);
        Consulta consultaExistente = consultaServicio.buscarConsultaPorId(consultaPK);
        if (consultaExistente == null) {
            throw new RecursoNoEncontradoExcepcion("Consulta no encontrada con el id de paciente: " + pacienteId + " y id de doctor: " + doctorId);
        }

        // Actualizamos los campos necesarios
        consultaExistente.setPaciente(consultaRecuperada.getPaciente());
        consultaExistente.setDoctor(consultaRecuperada.getDoctor());
        consultaExistente.setFechaConsulta(consultaRecuperada.getFechaConsulta());
        consultaExistente.setHoraConsulta(consultaRecuperada.getHoraConsulta());

        Consulta consultaActualizada = consultaServicio.guardarConsulta(consultaExistente);
        return ResponseEntity.ok(consultaActualizada);
    }


    @DeleteMapping("/consultas/{pacienteId}/{doctorId}")
    public ResponseEntity<Map<String, Boolean>> eliminarConsulta(@PathVariable Integer pacienteId, @PathVariable Integer doctorId) {
        ConsultaPK consultaPK = new ConsultaPK(pacienteId, doctorId);
        Consulta consulta = consultaServicio.buscarConsultaPorId(consultaPK);
        if (consulta == null) {
            throw new RecursoNoEncontradoExcepcion("Consulta no encontrada con el id de paciente: " + pacienteId + " y id de doctor: " + doctorId);
        }
        consultaServicio.eliminarConsulta(consulta);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
