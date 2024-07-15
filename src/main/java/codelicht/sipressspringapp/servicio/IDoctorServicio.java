package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Doctor;

import java.util.List;

public interface IDoctorServicio {
    public List<Doctor> listarDoctores();

    public Doctor buscarDoctorPorId(Integer idDoctor);

    public Doctor guardarDoctor(Doctor doctor);

    public void eliminarDoctor(Doctor doctor);
}
