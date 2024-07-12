package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-spring-app/empleados")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    // Método para listar todos los empleados
    // http://localhost:8080/sipress-spring-app/empleados
    @GetMapping
    public List<EmpleadoDTO> listarEmpleados() {
        List<EmpleadoDTO> empleados = empleadoServicio.listarRegistros();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    // Método para buscar un empleado por ID
    // http://localhost:8080/sipress-spring-app/empleados/{id}
    @GetMapping("/{id}")
    public EmpleadoDTO obtenerEmpleadoPorId(@PathVariable Integer id) {
        return empleadoServicio.buscarRegistroPorId(id);
    }

    // Método para guardar o actualizar un empleado
    // http://localhost:8080/sipress-spring-app/empleados
    @PostMapping
    public EmpleadoDTO guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        return empleadoServicio.guardarRegistro(empleadoDTO);
    }

    // Método para eliminar un empleado
    // http://localhost:8080/sipress-spring-app/empleados/{id}
    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Integer id) {
        empleadoServicio.eliminarRegistro(id);
    }

}
