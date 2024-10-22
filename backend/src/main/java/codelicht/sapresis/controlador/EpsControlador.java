package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Eps;
import codelicht.sapresis.servicio.interfaces.IEpsServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class EpsControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EpsControlador.class);

    private final IEpsServicio epsServicio;

    // Constructor para inyección de dependencias
    public EpsControlador(IEpsServicio epsServicio) {
        this.epsServicio = epsServicio;
    }

    // http://localhost:8080/sapresis/epsS
    @GetMapping("/epsS")
    @PreAuthorize("isAuthenticated()")
    public List<Eps> obtenerEpsS() {
        var epsS = epsServicio.listarEpsS();
        epsS.forEach((eps -> logger.info(eps.toString())));
        return epsS;
    }

    @GetMapping("/epsS/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<Eps> buscarEpsPorId(@PathVariable Integer id) {
        Eps eps = epsServicio.buscarEpsPorId(id);
        if (eps == null)
            throw new RecursoNoEncontradoExcepcion("Eps no encontrada con el id: " + id);
        return ResponseEntity.ok(eps);
    }

    @PostMapping("/epsS")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<?> agregarEps(@Valid @RequestBody Eps eps, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Eps a agregar: {}", eps);
        Eps nuevaEps = epsServicio.guardarEps(eps);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaEps.getIdEps())
                .toUri();
        return ResponseEntity.created(location).body(nuevaEps);
    }

    @PutMapping("/epsS/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<Eps> actualizarEps(@PathVariable Integer id, @RequestBody Eps epsRecuperada) {
        Eps eps = epsServicio.buscarEpsPorId(id);
        if (eps == null)
            throw new RecursoNoEncontradoExcepcion("Eps no encontrada con el id: " + id);
        eps.setIdEps(epsRecuperada.getIdEps());
        eps.setNombreEps(epsRecuperada.getNombreEps());
        eps.setTelefonoEps(epsRecuperada.getTelefonoEps());
        eps.setEmailEps(epsRecuperada.getEmailEps());
        epsServicio.guardarEps(eps);
        return ResponseEntity.ok(eps);
    }

    @DeleteMapping("/epsS/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminarEps(@PathVariable Integer id) {
        Eps eps = epsServicio.buscarEpsPorId(id);
        if (eps == null)
            throw new RecursoNoEncontradoExcepcion("Eps no encontrada con el id: " + id);
        epsServicio.eliminarEps(eps);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
