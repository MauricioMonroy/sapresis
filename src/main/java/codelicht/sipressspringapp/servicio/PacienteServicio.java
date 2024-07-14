package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
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

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Lista todos los registros de Paciente.
     *
     * @return una lista de todos los registros de Paciente.
     */
    @Override
    public List<PacienteDTO> listarRegistros() {
        return pacienteRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de Paciente por su ID.
     *
     * @param idPaciente el ID del paciente.
     * @return el paciente con el ID especificado, o null si no se encuentra.
     */
    @Override
    public PacienteDTO buscarRegistroPorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de Paciente o actualiza uno existente.
     *
     * @param pacienteDTO el paciente a guardar o actualizar.
     * @return el paciente guardado o actualizado.
     */
    @Override
    @Transactional
    public PacienteDTO guardarRegistro(PacienteDTO pacienteDTO) {
        Usuario usuario = convertirUsuarioAEntidad(pacienteDTO);
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        Paciente paciente = convertirPacienteAEntidad(pacienteDTO);
        paciente.setUsuario(usuarioGuardado);

        Paciente pacienteGuardado = pacienteRepositorio.save(paciente);
        return convertirADTO(pacienteGuardado);
    }

    /**
     * Elimina un registro de Paciente.
     *
     * @param idPaciente el paciente a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Integer idPaciente) {
        pacienteRepositorio.deleteById(idPaciente);
    }

    private Usuario convertirUsuarioAEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setIdentificacion(usuarioDTO.getIdentificacion());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEsPaciente(usuarioDTO.getEsPaciente());
        usuario.setEsEmpleado(usuarioDTO.getEsEmpleado());
        return usuario;
    }

    private Paciente convertirPacienteAEntidad(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setDetalleEps(pacienteDTO.getDetalleEps());
        paciente.setFechaConsulta(pacienteDTO.getFechaConsulta());
        return paciente;
    }

    private PacienteDTO convertirADTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setUsername(paciente.getUsername());
        dto.setPassword(paciente.getPassword());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setIdentificacion(paciente.getIdentificacion());
        dto.setTelefono(paciente.getTelefono());
        dto.setEmail(paciente.getEmail());
        dto.setEsPaciente(paciente.getEsPaciente());
        dto.setEsEmpleado(paciente.getEsEmpleado());
        dto.setDetalleEps(paciente.getDetalleEps());
        dto.setFechaConsulta(paciente.getFechaConsulta());
        return dto;
    }
}
