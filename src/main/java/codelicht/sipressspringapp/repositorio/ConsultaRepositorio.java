package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Consulta.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {
}
