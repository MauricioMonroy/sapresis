package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la entidad Usuario.
 */
@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    /**
     * Lista todos los registros de Usuario.
     *
     * @return una lista de todos los registros de Usuario.
     */
    @Override
    public List<UsuarioDTO> listarRegistros() {
        return usuarioRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de Usuario por su ID.
     *
     * @param idUsuario el ID del usuario.
     * @return el usuario con el ID especificado, o null si no se encuentra.
     */
    @Override
    public UsuarioDTO buscarRegistroPorId(Integer idUsuario) {
        return usuarioRepositorio.findById(idUsuario)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de Usuario o actualiza uno existente.
     * Guarda un registro de Paciente o Empleado si el usuario es paciente o empleado.
     *
     * @param usuarioDTO el usuario a guardar o actualizar.
     * @return el usuario guardado o actualizado.
     */
    @Override
    @Transactional
    public UsuarioDTO guardarRegistro(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertirAEntidad(usuarioDTO);
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        if (usuarioDTO.getEsPaciente()) {
            Paciente paciente = new Paciente();
            paciente.setId(usuarioGuardado.getId());
            pacienteRepositorio.save(paciente);
        }

        if (usuarioDTO.getEsEmpleado()) {
            Empleado empleado = new Empleado();
            empleado.setId(usuarioGuardado.getId());
            empleadoRepositorio.save(empleado);
        }

        return convertirADTO(usuarioGuardado);
    }

    /**
     * Elimina un registro de Usuario.
     *
     * @param idUsuario el paciente a eliminar.
     */
    @Override
    public void eliminarRegistro(Integer idUsuario) {
        usuarioRepositorio.deleteById(idUsuario);
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setPassword(usuario.getPassword());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setIdentificacion(usuario.getIdentificacion());
        dto.setTelefono(usuario.getTelefono());
        dto.setEmail(usuario.getEmail());
        dto.setEsPaciente(usuario.getEsPaciente());
        dto.setEsEmpleado(usuario.getEsEmpleado());
        return dto;
    }

    private Usuario convertirAEntidad(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setEsPaciente(dto.getEsPaciente());
        usuario.setEsEmpleado(dto.getEsEmpleado());
        return usuario;
    }
}
