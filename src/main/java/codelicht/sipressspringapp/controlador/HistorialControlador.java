package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.servicio.IHistorialServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historiales")
@CrossOrigin(value = "http://localhost:3000")
public class HistorialControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(HistorialControlador.class);

    @Autowired
    private IHistorialServicio historialServicio;

    // Método para listar todos los historiales
    @GetMapping
    public List<HistorialDTO> listarRegistros() {
        return historialServicio.listarRegistros();
    }

    // Método para buscar un historial por ID
    @GetMapping("/{id}")
    public HistorialDTO buscarRegistroPorId(@PathVariable Integer id) {
        return historialServicio.buscarRegistroPorId(id);
    }

    // Método para guardar o actualizar un historial
    @PostMapping
    public ResponseEntity<HistorialDTO> guardarRegistro(@RequestBody HistorialDTO historialDTO) {
        HistorialDTO nuevoHistorial = historialServicio.guardarRegistro(historialDTO);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    // Método para eliminar un historial
    @DeleteMapping("/{id}")
    public void eliminarRegistro(@PathVariable Integer id) {
        historialServicio.eliminarRegistro(id);
    }

}
