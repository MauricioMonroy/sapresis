package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class PacienteControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private IPacienteServicio pacienteServicio;

    // http://localhost:8080/sipress-app/pacientes
    @GetMapping("/pacientes")
    public List<Paciente> obtenerPacientes() {
        var pacientes = pacienteServicio.listarPacientes();
        pacientes.forEach((paciente -> logger.info(paciente.toString())));
        return pacientes;
    }

    @GetMapping("/pacientes/{id}")
    public Paciente buscarPacientePorId(@PathVariable Integer id) {
        return pacienteServicio.buscarPacientePorId(id);
    }

    @PostMapping("/pacientes")
    public Paciente agregarPaciente(@RequestBody Paciente paciente) {
        logger.info("Paciente a agregar: " + paciente);
        return pacienteServicio.guardarPaciente(paciente);
    }

    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable("id") Integer id) {
        Paciente paciente = pacienteServicio.buscarPacientePorId(id);
        if (paciente != null) {
            pacienteServicio.eliminarPaciente(paciente);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}

