package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Doctor;
import codelicht.sapresis.repositorio.DoctorRepositorio;
import codelicht.sapresis.servicio.interfaces.IDoctorServicio;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Doctor.
 */
@Service
public class DoctorServicio implements IDoctorServicio {

    private final DoctorRepositorio doctorRepositorio;

    // Inyección de dependencias por constructor
    public DoctorServicio(DoctorRepositorio doctorRepositorio) {
        this.doctorRepositorio = doctorRepositorio;
    }

    @Override
    public List<Doctor> listarDoctores() {
        return doctorRepositorio.findAll();
    }

    @Override
    public Doctor buscarDoctorPorId(Integer idDoctor) {
        return doctorRepositorio.findById(idDoctor).orElse(null);
    }

    @Override
    public Doctor guardarDoctor(Doctor doctor) {
        return doctorRepositorio.save(doctor);
    }

    @Override
    public void eliminarDoctor(Doctor doctor) {
        doctorRepositorio.delete(doctor);
    }
}
