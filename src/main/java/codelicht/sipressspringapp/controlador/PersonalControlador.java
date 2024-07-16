package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Personal;
import codelicht.sipressspringapp.servicio.interfaces.IPersonalServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class PersonalControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PersonalControlador.class);

    @Autowired
    private IPersonalServicio personalServicio;

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
    public Personal agregarPersonal(@RequestBody Personal personal) {
        logger.info("Personal a agregar: " + personal);
        return personalServicio.guardarPersonal(personal);
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
