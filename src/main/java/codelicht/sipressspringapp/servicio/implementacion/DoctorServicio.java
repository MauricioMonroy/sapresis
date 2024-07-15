package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Doctor;
import codelicht.sipressspringapp.repositorio.DoctorRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IDoctorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Doctor.
 */
@Service
public class DoctorServicio implements IDoctorServicio {

    @Autowired
    private DoctorRepositorio doctorRepositorio;

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
