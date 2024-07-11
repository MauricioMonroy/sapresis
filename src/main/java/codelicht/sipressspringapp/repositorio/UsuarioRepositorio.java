package codelicht.sipressspringapp.repositorio;

import codelicht.sipressspringapp.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}
