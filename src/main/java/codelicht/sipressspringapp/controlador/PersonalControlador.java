package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Personal;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPersonalServicio;
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
public class PersonalControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PersonalControlador.class);

    @Autowired
    private IPersonalServicio personalServicio;

    @Autowired
    private IDependenciaServicio dependenciaServicio;

    // http://localhost:8080/sipress-app/personalS
    @GetMapping("/personalS")
    public List<Personal> obtenerPersonalS() {
        var personalS = personalServicio.listarPersonalS();
        personalS.forEach((personal -> logger.info(personal.toString())));
        return personalS;
    }

    @GetMapping("/personalS/{id}")
    public Personal buscarPersonalPorId(@PathVariable Integer id) {
        return personalServicio.buscarPersonalPorId(id);
    }

    @PostMapping("/personalS")
    public ResponseEntity<?> agregarPersonal(@Valid @RequestBody Personal personal, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Personal a agregar: " + personal);
        if (personal.getDependencia() != null) {
            Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(personal.getDependencia().getIdDependencia());
            personal.setDependencia(dependencia);
        }
        Personal nuevoPersonal = personalServicio.guardarPersonal(personal);
        return ResponseEntity.ok(nuevoPersonal);
    }

    @DeleteMapping("/personalS/{id}")
    public ResponseEntity<Void> eliminarPersonal(@PathVariable("id") Integer id) {
        Personal personal = personalServicio.buscarPersonalPorId(id);
        if (personal != null) {
            personalServicio.eliminarPersonal(personal);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
