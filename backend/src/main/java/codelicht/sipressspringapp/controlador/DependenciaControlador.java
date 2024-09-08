package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Institucion;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IInstitucionServicio;
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
public class DependenciaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(DependenciaControlador.class);

    private final IDependenciaServicio dependenciaServicio;
    private final IInstitucionServicio institucionServicio;

    // Constructor para inyectar las dependencias
    public DependenciaControlador(IDependenciaServicio dependenciaServicio, IInstitucionServicio institucionServicio) {
        this.dependenciaServicio = dependenciaServicio;
        this.institucionServicio = institucionServicio;
    }

    // http://localhost:8080/sipress-app/dependencias
    @GetMapping("/dependencias")
    public List<Dependencia> obtenerDependencias() {
        var dependencias = dependenciaServicio.listarDependencias();
        dependencias.forEach((dependencia -> logger.info(dependencia.toString())));
        return dependencias;
    }

    @GetMapping("/dependencias/{id}")
    public ResponseEntity<Dependencia> buscarDependenciaPorId(@PathVariable Integer id) {
        Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(id);
        if (dependencia == null)
            throw new RecursoNoEncontradoExcepcion("Dependencia no encontrada con el id: " + id);
        return ResponseEntity.ok(dependencia);
    }

    @PostMapping("/dependencias")
    public ResponseEntity<?> agregarDependencia(@Valid @RequestBody Dependencia dependencia, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Dependencia a agregar: {}", dependencia);
        if (dependencia.getInstitucion() != null) {
            Institucion institucion = institucionServicio.buscarInstitucionPorId(dependencia.getInstitucion().getIdInstitucion());
            dependencia.setInstitucion(institucion);
        }
        Dependencia nuevaDependencia = dependenciaServicio.guardarDependencia(dependencia);
        return ResponseEntity.ok(nuevaDependencia);
    }

    @PutMapping("/dependencias/{id}")
    public ResponseEntity<Dependencia> actualizarDependencia(@PathVariable Integer id,
                                                             @RequestBody Dependencia dependenciaRecuperada) {
        Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(id);
        if (dependencia == null)
            throw new RecursoNoEncontradoExcepcion("Dependencia no encontrada con el id: " + id);
        dependencia.setIdDependencia(dependenciaRecuperada.getIdDependencia());
        dependencia.setNombreDependencia(dependenciaRecuperada.getNombreDependencia());
        dependencia.setInstitucion(dependenciaRecuperada.getInstitucion());
        dependenciaServicio.guardarDependencia(dependencia);
        return ResponseEntity.ok(dependencia);
    }

    @DeleteMapping("/dependencias/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDependencia(@PathVariable Integer id) {
        Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(id);
        if (dependencia == null)
            throw new RecursoNoEncontradoExcepcion("Dependencia no encontrada con el id: " + id);
        dependenciaServicio.eliminarDependencia(dependencia);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
