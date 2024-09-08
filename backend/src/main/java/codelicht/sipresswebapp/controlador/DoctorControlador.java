package codelicht.sipresswebapp.controlador;

import codelicht.sipresswebapp.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sipresswebapp.modelo.Dependencia;
import codelicht.sipresswebapp.modelo.Doctor;
import codelicht.sipresswebapp.servicio.interfaces.IDependenciaServicio;
import codelicht.sipresswebapp.servicio.interfaces.IDoctorServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class DoctorControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(DoctorControlador.class);

    private final IDoctorServicio doctorServicio;
    private final IDependenciaServicio dependenciaServicio;

    // Constructor para inyectar las dependencias
    public DoctorControlador(IDoctorServicio doctorServicio, IDependenciaServicio dependenciaServicio) {
        this.doctorServicio = doctorServicio;
        this.dependenciaServicio = dependenciaServicio;
    }

    // http://localhost:8080/sipress-app/doctores
    @GetMapping("/doctores")
    public List<Doctor> obtenerDoctors() {
        var doctores = doctorServicio.listarDoctores();
        doctores.forEach((doctor -> logger.info(doctor.toString())));
        return doctores;
    }

    @GetMapping("/doctores/{id}")
    public ResponseEntity<Doctor> buscarDoctorPorId(@PathVariable Integer id) {
        Doctor doctor = doctorServicio.buscarDoctorPorId(id);
        if (doctor == null)
            throw new RecursoNoEncontradoExcepcion("Doctor no encontrado con el id: " + id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping("/doctores")
    public ResponseEntity<?> agregarDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Doctor a agregar: {}", doctor);
        if (doctor.getDependencia() != null) {
            Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(doctor.getDependencia().getIdDependencia());
            doctor.setDependencia(dependencia);
        }
        Doctor nuevoDoctor = doctorServicio.guardarDoctor(doctor);
        return ResponseEntity.ok(nuevoDoctor);
    }

    @PutMapping("/doctores/{id}")
    public ResponseEntity<Doctor> actualizarDoctor(@PathVariable Integer id,
                                                   @RequestBody Doctor doctorRecuperado) {
        Doctor doctor = doctorServicio.buscarDoctorPorId(id);
        if (doctor == null)
            throw new RecursoNoEncontradoExcepcion("Doctor no encontrado con el id: " + id);
        doctor.setIdDoctor(doctorRecuperado.getIdDoctor());
        doctor.setNombreDoctor(doctorRecuperado.getNombreDoctor());
        doctor.setApellidoDoctor(doctorRecuperado.getApellidoDoctor());
        doctor.setDependencia(doctorRecuperado.getDependencia());
        doctor.setTelefonoDoctor(doctorRecuperado.getTelefonoDoctor());
        doctor.setEmailDoctor(doctorRecuperado.getEmailDoctor());
        doctorServicio.guardarDoctor(doctor);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/doctores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDoctor(@PathVariable Integer id) {
        Doctor doctor = doctorServicio.buscarDoctorPorId(id);
        if (doctor == null)
            throw new RecursoNoEncontradoExcepcion("Doctor no encontrado con el id: " + id);
        doctorServicio.eliminarDoctor(doctor);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
