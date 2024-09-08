package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Institucion.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface InstitucionRepositorio extends JpaRepository<Institucion, Integer> {
}
