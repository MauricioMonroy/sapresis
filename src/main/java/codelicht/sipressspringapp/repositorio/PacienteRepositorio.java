package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Integer> {
}