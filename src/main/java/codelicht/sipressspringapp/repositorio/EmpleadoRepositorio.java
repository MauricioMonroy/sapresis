package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Empleado.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}

