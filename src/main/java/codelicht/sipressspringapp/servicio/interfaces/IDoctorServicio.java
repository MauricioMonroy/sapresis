package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Doctor;

import java.util.List;

/**
 * Interface para la entidad Doctor.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IDoctorServicio {
    public List<Doctor> listarDoctores();

    public Doctor buscarDoctorPorId(Integer idDoctor);

    public Doctor guardarDoctor(Doctor doctor);

    public void eliminarDoctor(Doctor doctor);
}
