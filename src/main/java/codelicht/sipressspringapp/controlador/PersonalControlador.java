package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Personal;
import codelicht.sipressspringapp.servicio.interfaces.IPersonalServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class PersonalControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PersonalControlador.class);

    @Autowired
    private IPersonalServicio habitacionServicio;

    // http://localhost:8080/sipress-app/personalS
    @GetMapping("/personalS")
    public List<Personal> obtenerPersonalS() {
        var personalS = habitacionServicio.listarPersonalS();
        personalS.forEach((habitacion -> logger.info(habitacion.toString())));
        return personalS;
    }

    @PostMapping("/personalS")
    public Personal agregarPersonal(@RequestBody Personal habitacion) {
        logger.info("Personal a agregar: " + habitacion);
        return habitacionServicio.guardarPersonal(habitacion);
    }
}
