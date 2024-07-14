package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.modelo.Historial;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.HistorialRepositorio;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la entidad Historial.
 */
@Service
public class HistorialServicio implements IHistorialServicio {

    @Autowired
    private HistorialRepositorio historialRepositorio;

    private PacienteRepositorio pacienteRepositorio;

    /**
     * Lista todos los registros de Historial.
     *
     * @return una lista de todos los registros de Historial.
     */
    @Override
    public List<HistorialDTO> listarRegistros() {
        return historialRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de Historial por su ID.
     *
     * @param idHistorial el ID del historial.
     * @return el historial con el ID especificado, o null si no se encuentra.
     */
    @Override
    public HistorialDTO buscarRegistroPorId(Integer idHistorial) {
        return historialRepositorio.findById(idHistorial)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de Historial o actualiza uno existente.
     *
     * @param historialDTO el historial a guardar o actualizar.
     * @return el historial guardado o actualizado.
     */
    @Override
    @Transactional
    public HistorialDTO guardarRegistro(HistorialDTO historialDTO) {
        Paciente paciente = convertirPacienteAEntidad(historialDTO);
        Paciente pacienteGuardado = pacienteRepositorio.save(paciente);

        Historial historial = convertirHistorialAEntidad(historialDTO);
        historial.setPaciente(pacienteGuardado);

        Historial historialGuardado = historialRepositorio.save(historial);
        return convertirADTO(historialGuardado);
    }

    /**
     * Elimina un registro de historial.
     *
     * @param idHistorial el historial a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Integer idHistorial) {
        historialRepositorio.deleteById(idHistorial);
    }

    private Paciente convertirPacienteAEntidad(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setDetalleEps(pacienteDTO.getDetalleEps());
        paciente.setFechaConsulta(pacienteDTO.getFechaConsulta());
        return paciente;
    }

    private Historial convertirHistorialAEntidad(HistorialDTO dto) {
        Historial historial = new Historial();
        historial.setMotivoConsulta(dto.getMotivoConsulta());
        historial.setFechaNacimiento(dto.getFechaNacimiento());
        historial.setSexo(dto.getSexo());
        historial.setDireccion(dto.getDireccion());
        historial.setOcupacion(dto.getOcupacion());
        historial.setContactoEmergencia(dto.getContactoEmergencia());
        historial.setNombreContactoEmergencia(dto.getNombreContactoEmergencia());
        historial.setAlergias(dto.getAlergias());
        historial.setCondicionesPreexistentes(dto.getCondicionesPreexistentes());
        historial.setMedicamentosActuales(dto.getMedicamentosActuales());
        historial.setHistorialVacunas(dto.getHistorialVacunas());
        historial.setGrupoSanguineo(dto.getGrupoSanguineo());
        historial.setNotasAdicionales(dto.getNotasAdicionales());
        historial.setUltimaActualizacion(dto.getUltimaActualizacion());
        return historial;
    }

    private HistorialDTO convertirADTO(Historial historial) {
        HistorialDTO dto = new HistorialDTO();
        dto.setMotivoConsulta(historial.getMotivoConsulta());
        dto.setFechaNacimiento(historial.getFechaNacimiento());
        dto.setSexo(historial.getSexo());
        dto.setDireccion(historial.getDireccion());
        dto.setOcupacion(historial.getOcupacion());
        dto.setContactoEmergencia(historial.getContactoEmergencia());
        dto.setNombreContactoEmergencia(historial.getNombreContactoEmergencia());
        dto.setAlergias(historial.getAlergias());
        dto.setCondicionesPreexistentes(historial.getCondicionesPreexistentes());
        dto.setMedicamentosActuales(historial.getMedicamentosActuales());
        dto.setHistorialVacunas(historial.getHistorialVacunas());
        dto.setGrupoSanguineo(historial.getGrupoSanguineo());
        dto.setNotasAdicionales(historial.getNotasAdicionales());
        dto.setUltimaActualizacion(historial.getUltimaActualizacion());
        dto.setDetalleEps(historial.getDetalleEps());
        dto.setFechaConsulta(historial.getFechaConsulta());
        return dto;
    }
}
