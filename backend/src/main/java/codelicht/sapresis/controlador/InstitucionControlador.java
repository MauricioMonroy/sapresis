package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Institucion;
import codelicht.sapresis.servicio.interfaces.IInstitucionServicio;
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
//http://localhost:8080/sapresis/
@RequestMapping("sapresis")
@CrossOrigin(value = "http://localhost:3000")
public class InstitucionControlador {

    private static final Logger logger = LoggerFactory.getLogger(InstitucionControlador.class);

    private final IInstitucionServicio institucionServicio;

    // Constructor para inyectar las dependencias
    public InstitucionControlador(IInstitucionServicio institucionServicio) {
        this.institucionServicio = institucionServicio;
    }

    // http://localhost:8080/sapresis/instituciones
    @GetMapping("/instituciones")
    @PreAuthorize("isAuthenticated()")
    public List<Institucion> obtenerInstituciones() {
        var instituciones = institucionServicio.listarInstituciones();
        instituciones.forEach((institucion -> logger.info(institucion.toString())));
        return instituciones;
    }

    @GetMapping("/instituciones/{id}")
    public ResponseEntity<Institucion> buscarInstitucionPorId(@PathVariable Integer id) {
        Institucion institucion = institucionServicio.buscarInstitucionPorId(id);
        if (institucion == null)
            throw new RecursoNoEncontradoExcepcion("Institucion no encontrada con el id: " + id);
        return ResponseEntity.ok(institucion);
    }


    @PostMapping("/instituciones")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<?> agregarInstitucion(@Valid @RequestBody Institucion institucion, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Institucion a agregar: {}", institucion);
        Institucion nuevaInstitucion = institucionServicio.guardarInstitucion(institucion);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaInstitucion.getIdInstitucion())
                .toUri();
        return ResponseEntity.created(location).body(nuevaInstitucion);
    }

    @PutMapping("/instituciones/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<Institucion> actualizarInstitucion(@PathVariable Integer id,
                                                             @RequestBody Institucion institucionRecuperada) {
        Institucion institucion = institucionServicio.buscarInstitucionPorId(id);
        if (institucion == null)
            throw new RecursoNoEncontradoExcepcion("Institucion no encontrada con el id: " + id);
        institucion.setIdInstitucion(institucionRecuperada.getIdInstitucion());
        institucion.setNombreInstitucion(institucionRecuperada.getNombreInstitucion());
        institucion.setDireccionInstitucion(institucionRecuperada.getDireccionInstitucion());
        institucion.setTelefonoInstitucion(institucionRecuperada.getTelefonoInstitucion());
        institucion.setCodigoPostal(institucionRecuperada.getCodigoPostal());
        institucionServicio.guardarInstitucion(institucion);
        return ResponseEntity.ok(institucion);
    }

    @DeleteMapping("/instituciones/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminarInstitucion(@PathVariable Integer id) {
        Institucion institucion = institucionServicio.buscarInstitucionPorId(id);
        if (institucion == null)
            throw new RecursoNoEncontradoExcepcion("Institucion no encontrada con el id: " + id);
        institucionServicio.eliminarInstitucion(institucion);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
