package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.servicio.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(value = "http://localhost:3000")
public class PacienteControlador {

    @Autowired
    private IPacienteServicio pacienteServicio;

    // Método para listar todos los pacientes
    @GetMapping
    public List<PacienteDTO> listarRegistros() {
        return pacienteServicio.listarRegistros();
    }

    // Método para buscar un paciente por ID
    @GetMapping("/{id}")
    public PacienteDTO buscarRegistroPorId(@PathVariable Integer id) {
        return pacienteServicio.buscarRegistroPorId(id);
    }

    // Método para guardar o actualizar un paciente
    @PostMapping
    public ResponseEntity<PacienteDTO> guardarRegistro(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO nuevoPaciente = pacienteServicio.guardarRegistro(pacienteDTO);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    // Método para eliminar un paciente
    @DeleteMapping("/{id}")
    public void eliminarRegistro(@PathVariable Integer id) {
        pacienteServicio.eliminarRegistro(id);
    }
}



