package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepositorio extends JpaRepository<Historial, Integer> {
}
