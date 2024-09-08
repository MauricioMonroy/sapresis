package codelicht.sipresswebapp.repositorio;

import codelicht.sipresswebapp.modelo.Consulta;
import codelicht.sipresswebapp.modelo.ConsultaPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Consulta.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
public interface ConsultaRepositorio extends JpaRepository<Consulta, ConsultaPK> {

    List<Consulta> findByConsultaPK_PacienteId(Integer idPaciente);

    List<Consulta> findByConsultaPK_DoctorId(Integer idDoctor);
}

