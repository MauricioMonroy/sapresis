package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.servicio.IHistorialServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-spring-app/historiales")
@CrossOrigin(value = "http://localhost:3000")
public class HistorialControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(HistorialControlador.class);

    @Autowired
    private IHistorialServicio historialServicio;

    // http://localhost:8080/sipress-spring-app/historiales
    @GetMapping
    public List<HistorialDTO> listarHistoriales() {
        return historialServicio.listarRegistros();
    }

    @GetMapping("/{id}")
    public HistorialDTO obtenerHistorialPorId(@PathVariable Integer id) {
        return historialServicio.buscarRegistroPorId(id);
    }

    @PostMapping
    public HistorialDTO guardarHistorial(@RequestBody HistorialDTO historialDTO) {
        return historialServicio.guardarRegistro(historialDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorial(@PathVariable Integer id) {
        historialServicio.eliminarRegistro(id);
    }

}
