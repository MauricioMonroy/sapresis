package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consultorio;
import codelicht.sipressspringapp.servicio.interfaces.IConsultorioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultorioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultorioControlador.class);

    @Autowired
    private IConsultorioServicio consultorioServicio;

    // http://localhost:8080/sipress-app/consultorios
    @GetMapping("/consultorios")
    public List<Consultorio> obtenerHabitaciones() {
        var consultorios = consultorioServicio.listarConsultorios();
        consultorios.forEach((consultorio -> logger.info(consultorio.toString())));
        return consultorios;
    }

    @PostMapping("/consultorios")
    public Consultorio agregarHabitacion(@RequestBody Consultorio consultorio) {
        logger.info("Consultorio a agregar: " + consultorio);
        return consultorioServicio.guardarConsultorio(consultorio);
    }
}
