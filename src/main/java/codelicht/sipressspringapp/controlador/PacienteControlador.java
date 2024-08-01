package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sipressspringapp.modelo.Eps;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IEpsServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
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
public class PacienteControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PacienteControlador.class);

    private final IPacienteServicio pacienteServicio;
    private final IEpsServicio epsServicio;

    // Constructor para inyección de dependencias
    public PacienteControlador(IPacienteServicio pacienteServicio, IEpsServicio epsServicio) {
        this.pacienteServicio = pacienteServicio;
        this.epsServicio = epsServicio;
    }

    // http://localhost:8080/sipress-app/pacientes
    @GetMapping("/pacientes")
    public List<Paciente> obtenerPacientes() {
        var pacientes = pacienteServicio.listarPacientes();
        pacientes.forEach((paciente -> logger.info(paciente.toString())));
        return pacientes;
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        if (paciente == null)
            throw new RecursoNoEncontradoExcepcion("Paciente no encontrado con el id: " + id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping("/pacientes")
    public ResponseEntity<?> agregarPaciente(@Valid @RequestBody Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Paciente a agregar: {}", paciente);
        if (paciente.getEps() != null) {
            Eps eps = epsServicio.buscarEpsPorId(paciente.getEps().getIdEps());
            paciente.setEps(eps);
        }
        Paciente nuevoPaciente = pacienteServicio.guardarPaciente(paciente);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @PutMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id,
                                                       @RequestBody Paciente pacienteRecuperado) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        if (paciente == null)
            throw new RecursoNoEncontradoExcepcion("Paciente no encontrado con el id: " + id);
        paciente.setIdPaciente(pacienteRecuperado.getIdPaciente());
        paciente.setNombrePaciente(pacienteRecuperado.getNombrePaciente());
        paciente.setApellidoPaciente(pacienteRecuperado.getApellidoPaciente());
        paciente.setDireccionPaciente(pacienteRecuperado.getDireccionPaciente());
        paciente.setTelefonoPaciente(pacienteRecuperado.getTelefonoPaciente());
        paciente.setEmailPaciente(pacienteRecuperado.getEmailPaciente());
        paciente.setEps(pacienteRecuperado.getEps());
        pacienteServicio.guardarPaciente(paciente);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPaciente(@PathVariable Integer id) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        if (paciente == null)
            throw new RecursoNoEncontradoExcepcion("Paciente no encontrado con el id: " + id);
        pacienteServicio.eliminarPaciente(paciente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
