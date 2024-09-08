package codelicht.sipresswebapp.repositorio;

import codelicht.sipresswebapp.modelo.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Consultorio.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface ConsultorioRepositorio extends JpaRepository<Consultorio, Integer> {
}
