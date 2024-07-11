package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
