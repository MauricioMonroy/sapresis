package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.servicio.IHistorialServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sipress-spring-app")
@CrossOrigin(value = "http://localhost:3000")
public class HistorialControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(HistorialControlador.class);

    @Autowired
    private IHistorialServicio historialServicio;

}
