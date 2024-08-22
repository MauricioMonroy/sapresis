package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Paciente.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface PacienteRepositorio extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByNombrePacienteContaining(String nombrePaciente);
}
