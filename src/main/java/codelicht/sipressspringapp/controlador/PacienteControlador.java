package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.servicio.IPacienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sipress-spring-app/pacientes")
@CrossOrigin(value = "http://localhost:3000")
public class PacienteControlador {
    private static final Logger logger = LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private IPacienteServicio pacienteServicio;

    // Método para listar todos los pacientes
    // http://localhost:8080/sipress-spring-app/pacientes
    @GetMapping
    public List<PacienteDTO> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteServicio.listarRegistros();
        pacientes.forEach(paciente -> logger.info(paciente.toString()));
        return pacientes;
    }

    // Método para buscar un paciente por ID
    // http://localhost:8080/sipress-spring-app/pacientes/{id}
    @GetMapping("/{id}")
    public PacienteDTO buscarPacientePorId(@PathVariable Integer id) {
        return pacienteServicio.buscarRegistroPorId(id);
    }

    // Método para guardar o actualizar un paciente
    // http://localhost:8080/sipress-spring-app/pacientes
    @PostMapping
    public PacienteDTO guardarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return pacienteServicio.guardarRegistro(pacienteDTO);
    }

    // Método para eliminar un paciente
    // http://localhost:8080/sipress-spring-app/pacientes/{id}
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Integer id) {
        pacienteServicio.eliminarRegistro(id);
    }
}



