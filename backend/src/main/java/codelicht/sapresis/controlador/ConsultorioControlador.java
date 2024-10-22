package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Consultorio;
import codelicht.sapresis.modelo.Paciente;
import codelicht.sapresis.modelo.Personal;
import codelicht.sapresis.servicio.interfaces.IConsultorioServicio;
import codelicht.sapresis.servicio.interfaces.IPacienteServicio;
import codelicht.sapresis.servicio.interfaces.IPersonalServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sapresis")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultorioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultorioControlador.class);

    private final IConsultorioServicio consultorioServicio;
    private final IPacienteServicio pacienteServicio;
    private final IPersonalServicio personalServicio;

    // Constructor para inyección de dependencias
    public ConsultorioControlador(IConsultorioServicio consultorioServicio, IPacienteServicio pacienteServicio, IPersonalServicio personalServicio) {
        this.consultorioServicio = consultorioServicio;
        this.pacienteServicio = pacienteServicio;
        this.personalServicio = personalServicio;
    }

    // http://localhost:8080/sapresis/consultorios
    @GetMapping("/consultorios")
    public List<Consultorio> obtenerConsultorios() {
        var consultorios = consultorioServicio.listarConsultorios();
        consultorios.forEach((consultorio -> logger.info(consultorio.toString())));
        return consultorios;
    }

    @GetMapping("/consultorios/{id}")
    public ResponseEntity<Consultorio> buscarConsultorioPorId(@PathVariable Integer id) {
        Consultorio consultorio = consultorioServicio.buscarConsultorioPorId(id);
        if (consultorio == null)
            throw new RecursoNoEncontradoExcepcion("Consultorio no encontrado con el número: " + id);
        return ResponseEntity.ok(consultorio);
    }

    @PostMapping("/consultorios")
    public ResponseEntity<?> agregarConsultorio(@Valid @RequestBody Consultorio consultorio, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Consultorio a agregar: {}", consultorio);
        if (consultorio.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(consultorio.getPaciente().getIdPaciente());
            consultorio.setPaciente(paciente);
        }
        if (consultorio.getPersonal() != null) {
            Personal personal = personalServicio.buscarPersonalPorId(consultorio.getPersonal().getIdPersonal());
            consultorio.setPersonal(personal);
        }
        Consultorio nuevoConsultorio = consultorioServicio.guardarConsultorio(consultorio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoConsultorio.getNumeroConsultorio())
                .toUri();
        return ResponseEntity.created(location).body(nuevoConsultorio);
    }

    @PutMapping("/consultorios/{id}")
    public ResponseEntity<Consultorio> actualizarConsultorio(@PathVariable Integer id,
                                                             @RequestBody Consultorio consultorioRecuperado) {
        Consultorio consultorio = consultorioServicio.buscarConsultorioPorId(id);
        if (consultorio == null)
            throw new RecursoNoEncontradoExcepcion("Consultorio no encontrado con el número: " + id);
        consultorio.setNumeroConsultorio(consultorioRecuperado.getNumeroConsultorio());
        consultorio.setPaciente(consultorioRecuperado.getPaciente());
        consultorio.setPersonal(consultorioRecuperado.getPersonal());
        consultorio.setFechaAdmision(consultorioRecuperado.getFechaAdmision());
        consultorioServicio.guardarConsultorio(consultorio);
        return ResponseEntity.ok(consultorio);
    }

    @DeleteMapping("/consultorios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarConsultorio(@PathVariable Integer id) {
        Consultorio consultorio = consultorioServicio.buscarConsultorioPorId(id);
        if (consultorio == null)
            throw new RecursoNoEncontradoExcepcion("Consultorio no encontrado con el número: " + id);
        consultorioServicio.eliminarConsultorio(consultorio);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
