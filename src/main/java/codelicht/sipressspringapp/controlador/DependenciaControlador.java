package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Institucion;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class DependenciaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(DependenciaControlador.class);

    @Autowired
    private IDependenciaServicio dependenciaServicio;

    @Autowired
    private InstitucionControlador institucionServicio;

    // http://localhost:8080/sipress-app/dependencias
    @GetMapping("/dependencias")
    public List<Dependencia> obtenerDependencias() {
        var dependencias = dependenciaServicio.listarDependencias();
        dependencias.forEach((dependencia -> logger.info(dependencia.toString())));
        return dependencias;
    }

    @GetMapping("/dependencias/{id}")
    public Dependencia buscarDependenciaPorId(@PathVariable Integer id) {
        return dependenciaServicio.buscarDependenciaPorId(id);
    }

    @PostMapping("/dependencias")
    public Dependencia agregarDependencia(@RequestBody Dependencia dependencia) {
        logger.info("Dependencia a agregar: " + dependencia);
        if (dependencia.getInstitucion() != null) {
            Institucion institucion = institucionServicio.buscarInstitucionPorId(dependencia.getInstitucion().getIdInstitucion());
            dependencia.setInstitucion(institucion);
        }
        return dependenciaServicio.guardarDependencia(dependencia);
    }

    @DeleteMapping("/dependencias/{id}")
    public ResponseEntity<Void> eliminarDependencia(@PathVariable("id") Integer id) {
        Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(id);
        if (dependencia != null) {
            dependenciaServicio.eliminarDependencia(dependencia);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
