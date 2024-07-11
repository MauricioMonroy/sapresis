package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Usuario.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}

