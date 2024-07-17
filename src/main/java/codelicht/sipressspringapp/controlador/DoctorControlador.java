package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.servicio.interfaces.IDependenciaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Doctor agregarDoctor(@RequestBody Doctor doctor) {
        logger.info("Doctor a agregar: " + doctor);
        if (doctor.getDependencia() != null) {
            Dependencia dependencia = dependenciaServicio.buscarDependenciaPorId(doctor.getDependencia().getIdDependencia());
            doctor.setDependencia(dependencia);
        }
        return doctorServicio.guardarDoctor(doctor);
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
