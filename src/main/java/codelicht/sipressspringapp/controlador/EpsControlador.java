package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Eps;
import codelicht.sipressspringapp.servicio.interfaces.IEpsServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class EpsControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EpsControlador.class);

    @Autowired
    private IEpsServicio dependenciaServicio;

    // http://localhost:8080/sipress-app/epsS
    @GetMapping("/epsS")
    public List<Eps> obtenerEpsS() {
        var epsS = dependenciaServicio.listarEpsS();
        epsS.forEach((dependencia -> logger.info(dependencia.toString())));
        return epsS;
    }

    @PostMapping("/epsS")
    public Eps agregarEps(@RequestBody Eps dependencia) {
        logger.info("Eps a agregar: " + dependencia);
        return dependenciaServicio.guardarEps(dependencia);
    }
}
