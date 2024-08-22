package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Doctor.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface DoctorRepositorio extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByNombreDoctorContaining(String nombreDoctor);
}