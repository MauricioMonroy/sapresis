package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Consultorio;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.modelo.Personal;
import codelicht.sipressspringapp.servicio.interfaces.IConsultorioServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPersonalServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class ConsultorioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ConsultorioControlador.class);

    @Autowired
    private IConsultorioServicio consultorioServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    @Autowired
    private IPersonalServicio personalServicio;

    // http://localhost:8080/sipress-app/consultorios
    @GetMapping("/consultorios")
    public List<Consultorio> obtenerConsultorios() {
        var consultorios = consultorioServicio.listarConsultorios();
        consultorios.forEach((consultorio -> logger.info(consultorio.toString())));
        return consultorios;
    }

    @GetMapping("/consultorios/{id}")
    public Consultorio buscarConsultorioPorId(@PathVariable Integer id) {
        return consultorioServicio.buscarConsultorioPorId(id);
    }

    @PostMapping("/consultorios")
    public Consultorio agregarConsultorio(@RequestBody Consultorio consultorio) {
        logger.info("Consultorio a agregar: {}", consultorio);
        if (consultorio.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(consultorio.getPaciente().getIdPaciente());
            consultorio.setPaciente(paciente);
        }
        if (consultorio.getPersonal() != null) {
            Personal personal = personalServicio.buscarPersonalPorId(consultorio.getPersonal().getIdPersonal());
            consultorio.setPersonal(personal);
        }
        return consultorioServicio.guardarConsultorio(consultorio);
    }

    @DeleteMapping("/consultorios/{id}")
    public ResponseEntity<Void> eliminarConsultorio(@PathVariable("id") Integer id) {
        Consultorio consultorio = consultorioServicio.buscarConsultorioPorId(id);
        if (consultorio != null) {
            consultorioServicio.eliminarConsultorio(consultorio);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
