package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.servicio.interfaces.IConsultaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultaControlador.class);

    @Autowired
    private IConsultaServicio consultaServicio;

    // http://localhost:8080/sipress-app/consultas
    @GetMapping("/consultas")
    public List<Consulta> obtenerConsultas() {
        var consultas = consultaServicio.listarConsultas();
        consultas.forEach((consulta -> logger.info(consulta.toString())));
        return consultas;
    }

    @PostMapping("/consultas")
    public Consulta agregarConsulta(@RequestBody Consulta consulta) {
        logger.info("Consulta a agregar: " + consulta);
        return consultaServicio.guardarConsulta(consulta);
    }
}
