package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Dependencia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Dependencia.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface DependenciaRepositorio extends JpaRepository<Dependencia, Integer> {
}
