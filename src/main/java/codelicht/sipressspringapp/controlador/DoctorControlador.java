package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // http://localhost:8080/sipress-app/doctores
    @GetMapping("/doctores")
    public List<Doctor> obtenerDoctors() {
        var doctores = doctorServicio.listarDoctores();
        doctores.forEach((doctor -> logger.info(doctor.toString())));
        return doctores;
    }

    @PostMapping("/doctores")
    public Doctor agregarDoctor(@RequestBody Doctor doctor) {
        logger.info("Doctor a agregar: " + doctor);
        return doctorServicio.guardarDoctor(doctor);
    }
}
