package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-spring-app/empleados")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    // http://localhost:8080/sipress-spring-app/empleados
    @GetMapping()
    public List<EmpleadoDTO> listarEmpleados() {
        List<Empleado> empleados = empleadoServicio.listarRegistros();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private EmpleadoDTO convertirADTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setCargo(empleado.getCargo());
        dto.setSueldo(empleado.getSueldo());
        return dto;
    }

}
