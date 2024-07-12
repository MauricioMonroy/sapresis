package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio para la entidad Paciente.
 */
@Service
public class PacienteServicio implements IPacienteServicio {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    /**
     * Lista todos los registros de pacientes.
     *
     * @return una lista de todos los pacientes.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listarRegistros() {
        return pacienteRepositorio.findAll();
    }

    /**
     * Busca un registro de paciente por su ID.
     *
     * @param idPaciente el ID del paciente.
     * @return el paciente con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Paciente buscarRegistroPorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente).orElse(null);
    }

    /**
     * Guarda un nuevo registro de paciente o actualiza uno existente.
     *
     * @param paciente el paciente a guardar o actualizar.
     * @return el paciente guardado o actualizado.
     */
    @Override
    @Transactional
    public Paciente guardarRegistro(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    /**
     * Elimina un registro de paciente.
     *
     * @param paciente el paciente a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Paciente paciente) {
        pacienteRepositorio.delete(paciente);
    }
}
