package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PacienteDTO> listarRegistros() {
        return pacienteRepositorio.findAll().stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de paciente por su ID.
     *
     * @param idPaciente el ID del paciente.
     * @return el paciente con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public PacienteDTO buscarRegistroPorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente)
                .map(this::convertirEntidadADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de paciente o actualiza uno existente.
     *
     * @param pacienteDTO el paciente a guardar o actualizar.
     * @return el paciente guardado o actualizado.
     */
    @Override
    @Transactional
    public PacienteDTO guardarRegistro(PacienteDTO pacienteDTO) {
        Paciente paciente = convertirDTOAEntidad(pacienteDTO);
        Paciente pacienteGuardado = pacienteRepositorio.save(paciente);
        return convertirEntidadADTO(pacienteGuardado);
    }

    /**
     * Elimina un registro de paciente.
     *
     * @param idPaciente el ID del paciente a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Integer idPaciente) {
        pacienteRepositorio.deleteById(idPaciente);
    }

    private PacienteDTO convertirEntidadADTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setDetalleEps(paciente.getDetalleEps());
        pacienteDTO.setFechaConsulta(paciente.getFechaConsulta());
        return pacienteDTO;
    }

    private Paciente convertirDTOAEntidad(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setDetalleEps(pacienteDTO.getDetalleEps());
        paciente.setFechaConsulta(pacienteDTO.getFechaConsulta());
        return paciente;
    }
}

