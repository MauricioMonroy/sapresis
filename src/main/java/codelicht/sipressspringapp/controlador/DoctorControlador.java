package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class DoctorControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(DoctorControlador.class);

    @Autowired
    private IDoctorServicio doctorServicio;

    @Autowired
    private IDependenciaServicio dependenciaServicio;

    // http://localhost:8080/sipress-app/doctores
    @GetMapping("/doctores")
    public List<Doctor> obtenerDoctors() {
        var doctores = doctorServicio.listarDoctores();
        doctores.forEach((doctor -> logger.info(doctor.toString())));
        return doctores;
    }

    @GetMapping("/doctores/{id}")
    public Doctor buscarDoctorPorId(@PathVariable Integer id) {
        return doctorServicio.buscarDoctorPorId(id);
    }

    @PostMapping("/doctores")
    public ResponseEntity<?> agregarDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Doctor a agregar: " + doctor);
        if (doctor.getDependencia() != null) {
            Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(doctor.getDependencia().getIdDependencia());
            doctor.setDependencia(dependencia);
        }
        Doctor nuevoDoctor = doctorServicio.guardarDoctor(doctor);
        return ResponseEntity.ok(nuevoDoctor);
    }

    @DeleteMapping("/doctores/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable("id") Integer id) {
        Doctor doctor = doctorServicio.buscarDoctorPorId(id);
        if (doctor != null) {
            doctorServicio.eliminarDoctor(doctor);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
