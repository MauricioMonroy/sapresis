package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // http://localhost:8080/sipress-app/dependencias
    @GetMapping("/dependencias")
    public List<Dependencia> obtenerDependencias() {
        var dependencias = dependenciaServicio.listarDependencias();
        dependencias.forEach((dependencia -> logger.info(dependencia.toString())));
        return dependencias;
    }

    @PostMapping("/dependencias")
    public Dependencia agregarDependencia(@RequestBody Dependencia dependencia) {
        logger.info("Dependencia a agregar: " + dependencia);
        return dependenciaServicio.guardarDependencia(dependencia);
    }
}
