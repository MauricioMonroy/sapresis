package codelicht.sapresis.repositorio;

import codelicht.sapresis.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Paciente.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface PacienteRepositorio extends JpaRepository<Paciente, Integer> {
}
