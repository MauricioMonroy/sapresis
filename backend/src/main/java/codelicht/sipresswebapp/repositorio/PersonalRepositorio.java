package codelicht.sipresswebapp.repositorio;

import codelicht.sipresswebapp.modelo.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Personal.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface PersonalRepositorio extends JpaRepository<Personal, Integer> {
}

