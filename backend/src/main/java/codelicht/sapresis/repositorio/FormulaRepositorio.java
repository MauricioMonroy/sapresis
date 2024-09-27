package codelicht.sapresis.repositorio;

import codelicht.sapresis.modelo.Formula;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Fórmula.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface FormulaRepositorio extends JpaRepository<Formula, Integer> {
}
