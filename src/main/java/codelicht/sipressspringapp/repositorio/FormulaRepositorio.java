package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Formula;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Formula.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface FormulaRepositorio extends JpaRepository<Formula, Integer> {
}
