package codelicht.sipresswebapp.repositorio;

import codelicht.sipresswebapp.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Factura.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
}
