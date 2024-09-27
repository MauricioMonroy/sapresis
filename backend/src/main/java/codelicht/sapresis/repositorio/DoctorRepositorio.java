package codelicht.sapresis.repositorio;

import codelicht.sapresis.modelo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Doctor.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface DoctorRepositorio extends JpaRepository<Doctor, Integer> {
}