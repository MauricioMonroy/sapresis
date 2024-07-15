package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Habitacion.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface HabitacionRepositorio extends JpaRepository<Habitacion, Integer> {
}
