package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Dependencia;
import codelicht.sapresis.modelo.Personal;
import codelicht.sapresis.servicio.interfaces.IDependenciaServicio;
import codelicht.sapresis.servicio.interfaces.IPersonalServicio;
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
public class PersonalControlador {
    private static final Logger logger = LoggerFactory.getLogger(PersonalControlador.class);

    private final IPersonalServicio personalServicio;
    private final IDependenciaServicio dependenciaServicio;

    // Constructor para inyectar las dependencias
    public PersonalControlador(IPersonalServicio personalServicio, IDependenciaServicio dependenciaServicio) {
        this.personalServicio = personalServicio;
        this.dependenciaServicio = dependenciaServicio;
    }

    // http://localhost:8080/sipress-app/personalS
    @GetMapping("/personalS")
    public List<Personal> obtenerPersonalS() {
        var personalS = personalServicio.listarPersonalS();
        personalS.forEach((personal -> logger.info(personal.toString())));
        return personalS;
    }

    @GetMapping("/personalS/{id}")
    public ResponseEntity<Personal> buscarPersonalPorId(@PathVariable Integer id) {
        Personal personal = personalServicio.buscarPersonalPorId(id);
        if (personal == null)
            throw new RecursoNoEncontradoExcepcion("Personal no encontrado con el id: " + id);
        return ResponseEntity.ok(personal);
    }

    @PostMapping("/personalS")
    public ResponseEntity<?> agregarPersonal(@Valid @RequestBody Personal personal, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Personal a agregar: {}", personal);
        if (personal.getDependencia() != null) {
            Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(personal.getDependencia().getIdDependencia());
            personal.setDependencia(dependencia);
        }
        Personal nuevoPersonal = personalServicio.guardarPersonal(personal);
        return ResponseEntity.ok(nuevoPersonal);
    }

    @PutMapping("/personalS/{id}")
    public ResponseEntity<Personal> actualizarPersonal(@PathVariable Integer id, @RequestBody Personal personalRecuperado) {
        Personal personal = personalServicio.buscarPersonalPorId(id);
        if (personal == null)
            throw new RecursoNoEncontradoExcepcion("Personal no encontrado con el id: " + id);
        personal.setIdPersonal(personalRecuperado.getIdPersonal());
        personal.setDependencia(personalRecuperado.getDependencia());
        personal.setNombrePersonal(personalRecuperado.getNombrePersonal());
        personal.setApellidoPersonal(personalRecuperado.getApellidoPersonal());
        personal.setTelefonoPersonal(personalRecuperado.getTelefonoPersonal());
        personal.setEmailPersonal(personalRecuperado.getEmailPersonal());
        personal.setDependencia(personalRecuperado.getDependencia());
        personalServicio.guardarPersonal(personal);
        return ResponseEntity.ok(personal);
    }

    @DeleteMapping("/personalS/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPersonal(@PathVariable Integer id) {
        Personal personal = personalServicio.buscarPersonalPorId(id);
        if (personal == null)
            throw new RecursoNoEncontradoExcepcion("Personal no encontrado con el id: " + id);
        personalServicio.eliminarPersonal(personal);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

