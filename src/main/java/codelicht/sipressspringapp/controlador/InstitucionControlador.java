package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Institucion;
import codelicht.sipressspringapp.servicio.interfaces.IInstitucionServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/sipress-app/
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class InstitucionControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(InstitucionControlador.class);

    @Autowired
    private IInstitucionServicio institucionServicio;

    // http://localhost:8080/sipress-app/instituciones
    @GetMapping("/instituciones")
    public List<Institucion> obtenerInstituciones() {
        var institucions = institucionServicio.listarInstituciones();
        institucions.forEach((institucion -> logger.info(institucion.toString())));
        return institucions;
    }

    @GetMapping("/instituciones/{id}")
    public Institucion buscarInstitucionPorId(@PathVariable Integer id) {
        return institucionServicio.buscarInstitucionPorId(id);
    }

    @PostMapping("/instituciones")
    public Institucion agregarInstitucion(@RequestBody Institucion institucion) {
        logger.info("Institucion a agregar: " + institucion);
        return institucionServicio.guardarInstitucion(institucion);
    }

    @DeleteMapping("/instituciones/{id}")
    public ResponseEntity<Void> eliminarInstitucion(@PathVariable("id") Integer id) {
        Institucion institucion = institucionServicio.buscarInstitucionPorId(id);
        if (institucion != null) {
            institucionServicio.eliminarInstitucion(institucion);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
