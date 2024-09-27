package codelicht.sapresis.auth.repositorio;

import codelicht.sapresis.auth.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de la entidad Usuario
 * Extiende de CrudRepository y recibe la entidad Usuario y el tipo de dato de su llave primaria
 */
@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
