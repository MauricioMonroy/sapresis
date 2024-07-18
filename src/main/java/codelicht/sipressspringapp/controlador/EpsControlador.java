package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Eps;
import codelicht.sipressspringapp.servicio.interfaces.IEpsServicio;
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
public class EpsControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EpsControlador.class);

    @Autowired
    private IEpsServicio epsServicio;

    // http://localhost:8080/sipress-app/epsS
    @GetMapping("/epsS")
    public List<Eps> obtenerEpsS() {
        var epsS = epsServicio.listarEpsS();
        epsS.forEach((eps -> logger.info(eps.toString())));
        return epsS;
    }

    @GetMapping("/epsS/{id}")
    public Eps buscarEpsPorId(@PathVariable Integer id) {
        return epsServicio.buscarEpsPorId(id);
    }

    @PostMapping("/epsS")
    public ResponseEntity<?> agregarEps(@Valid @RequestBody Eps eps, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Eps a agregar: " + eps);
        Eps nuevaEps = epsServicio.guardarEps(eps);
        return ResponseEntity.ok(nuevaEps);
    }

    @DeleteMapping("/epsS/{id}")
    public ResponseEntity<Void> eliminarEps(@PathVariable("id") Integer id) {
        Eps eps = epsServicio.buscarEpsPorId(id);
        if (eps != null) {
            epsServicio.eliminarEps(eps);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
