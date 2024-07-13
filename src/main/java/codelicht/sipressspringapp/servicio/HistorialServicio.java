package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.modelo.Historial;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.HistorialRepositorio;
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

    /**
     * Lista todos los registros de historiales.
     *
     * @return una lista de todos los historiales.
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistorialDTO> listarRegistros() {
        return historialRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de historial por su ID.
     *
     * @param idHistorial el ID del historial.
     * @return el historial con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public HistorialDTO buscarRegistroPorId(Integer idHistorial) {
        return historialRepositorio.findById(idHistorial)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de historial o actualiza uno existente.
     *
     * @param historialDTO el historial a guardar o actualizar.
     * @return el historial guardado o actualizado.
     */
    @Override
    @Transactional
    public HistorialDTO guardarRegistro(HistorialDTO historialDTO) {
        Historial historial = convertirAEntidad(historialDTO);
        return convertirADTO(historialRepositorio.save(historial));
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
    private HistorialDTO convertirADTO(Historial historial) {
        HistorialDTO dto = new HistorialDTO();
        dto.setId(historial.getId());
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

//        PacienteDTO pacienteDTO = new PacienteDTO();
//        Paciente paciente = historial.getIdUsuario();
//        pacienteDTO.setId(paciente.getId());
//        pacienteDTO.setDetalleEps(paciente.getDetalleEps());
//        pacienteDTO.setFechaConsulta(paciente.getFechaConsulta());
//
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setId(paciente.getIdUsuario().getId());
//        usuarioDTO.setNombre(paciente.getIdUsuario().getNombre());
//        usuarioDTO.setApellido(paciente.getIdUsuario().getApellido());
//        usuarioDTO.setIdentificacion(paciente.getIdUsuario().getIdentificacion());
//        usuarioDTO.setTelefono(paciente.getIdUsuario().getTelefono());
//        usuarioDTO.setEmail(paciente.getIdUsuario().getEmail());
//
//        pacienteDTO.setUsuario(usuarioDTO);

//        dto.setPaciente(pacienteDTO);

        return dto;
    }

    private Historial convertirAEntidad(HistorialDTO dto) {
        Historial historial = new Historial();
        historial.setId(dto.getId());
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

//        PacienteDTO pacienteDTO = dto.getPaciente();
//        if (pacienteDTO != null) {
//            Paciente paciente = new Paciente();
//            paciente.setId(pacienteDTO.getId());
//            paciente.setDetalleEps(pacienteDTO.getDetalleEps());
//            paciente.setFechaConsulta(pacienteDTO.getFechaConsulta());
//
//            UsuarioDTO usuarioDTO = pacienteDTO.getUsuario();
//            if (usuarioDTO != null) {
//                Usuario usuario = new Usuario();
//                usuario.setId(usuarioDTO.getId());
//                usuario.setNombre(usuarioDTO.getNombre());
//                usuario.setApellido(usuarioDTO.getApellido());
//                usuario.setIdentificacion(usuarioDTO.getIdentificacion());
//                usuario.setTelefono(usuarioDTO.getTelefono());
//                usuario.setEmail(usuarioDTO.getEmail());
//            }
//
//            historial.setIdPaciente(paciente);
//        }

        return historial;
    }
}
