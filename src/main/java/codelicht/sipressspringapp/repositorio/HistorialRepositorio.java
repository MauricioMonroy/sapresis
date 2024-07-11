package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Historial.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface HistorialRepositorio extends JpaRepository<Historial, Integer> {
}

