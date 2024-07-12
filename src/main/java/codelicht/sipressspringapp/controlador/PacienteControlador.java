package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.PacienteServicio;
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
@RequestMapping("/sipress-spring-app/pacientes")
@CrossOrigin(value = "http://localhost:3000")
public class PacienteControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private PacienteServicio pacienteServicio;

    // http://localhost:8080/sipress-spring-app/pacientes
    @GetMapping
    public List<PacienteDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteServicio.listarRegistros();
        pacientes.forEach((paciente -> logger.info(paciente.toString())));
        return pacientes.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private PacienteDTO convertirADTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setDetalleEps(paciente.getDetalleEps());
        dto.setFechaConsulta(paciente.getFechaConsulta());
        return dto;
    }
}


