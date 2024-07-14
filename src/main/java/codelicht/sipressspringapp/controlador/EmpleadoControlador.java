package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    // Método para listar todos los empleados
    @GetMapping
    public List<EmpleadoDTO> listarRegistros() {
        return empleadoServicio.listarRegistros();
    }

    // Método para buscar un empleado por ID
    @GetMapping("/{id}")
    public EmpleadoDTO buscarRegistroPorId(@PathVariable Integer id) {
        return empleadoServicio.buscarRegistroPorId(id);
    }

    // Método para guardar o actualizar un empleado
    @PostMapping
    public ResponseEntity<EmpleadoDTO> guardarRegistro(@RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO nuevoEmpleado = empleadoServicio.guardarRegistro(empleadoDTO);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    // Método para eliminar un empleado
    @DeleteMapping("/{id}")
    public void eliminarRegistro(@PathVariable Integer id) {
        empleadoServicio.eliminarRegistro(id);
    }

}
