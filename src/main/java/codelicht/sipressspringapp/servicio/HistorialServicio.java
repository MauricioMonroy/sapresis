package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.modelo.Historial;
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
    public HistorialDTO guardarRegistro(HistorialDTO historialDTO) {
        Historial historial = convertirAEntidad(historialDTO);
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

    private HistorialDTO convertirADTO(Historial historial) {
        HistorialDTO dto = new HistorialDTO();
        // Mapea los campos de Usuario
        dto.setId(historial.getId());
        dto.setUsername(historial.getUsername());
        dto.setPassword(historial.getPassword());
        dto.setNombre(historial.getNombre());
        dto.setApellido(historial.getApellido());
        dto.setIdentificacion(historial.getIdentificacion());
        dto.setTelefono(historial.getTelefono());
        dto.setEmail(historial.getEmail());
        dto.setEsPaciente(historial.getEsPaciente());
        dto.setEsEmpleado(historial.getEsEmpleado());

        // Mapea los campos de Paciente
        dto.setDetalleEps(historial.getDetalleEps());
        dto.setFechaConsulta(historial.getFechaConsulta());

        // Mapea los campos específicos de Historial
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

        return dto;
    }

    private Historial convertirAEntidad(HistorialDTO dto) {
        Historial historial = new Historial();
        // Mapea los campos de Usuario
        historial.setId(dto.getId());
        historial.setUsername(dto.getUsername());
        historial.setPassword(dto.getPassword());
        historial.setNombre(dto.getNombre());
        historial.setApellido(dto.getApellido());
        historial.setIdentificacion(dto.getIdentificacion());
        historial.setTelefono(dto.getTelefono());
        historial.setEmail(dto.getEmail());
        historial.setEsPaciente(dto.getEsPaciente());
        historial.setEsEmpleado(dto.getEsEmpleado());

        // Mapea los campos de Paciente
        historial.setDetalleEps(dto.getDetalleEps());
        historial.setFechaConsulta(dto.getFechaConsulta());

        // Mapea los campos específicos de Historial
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
}
