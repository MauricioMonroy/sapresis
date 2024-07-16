package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Eps;
import codelicht.sipressspringapp.servicio.interfaces.IEpsServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Eps agregarEps(@RequestBody Eps eps) {
        logger.info("Eps a agregar: " + eps);
        return epsServicio.guardarEps(eps);
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
