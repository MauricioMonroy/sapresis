package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/pacientes")
    public Paciente agregarPaciente(@RequestBody Paciente paciente) {
        logger.info("Paciente a agregar: " + paciente);
        return pacienteServicio.guardarPaciente(paciente);
    }
}
