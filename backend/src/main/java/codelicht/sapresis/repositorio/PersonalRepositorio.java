package codelicht.sapresis.repositorio;

import codelicht.sapresis.modelo.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Personal.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface PersonalRepositorio extends JpaRepository<Personal, Integer> {
}

