package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la entidad Empleado.
 */
@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Lista todos los registros de Empleado.
     *
     * @return una lista de todos los registros de Empleado.
     */
    @Override
    public List<EmpleadoDTO> listarRegistros() {
        return empleadoRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de Empleado por su ID.
     *
     * @param idEmpleado el ID del empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    @Override
    public EmpleadoDTO buscarRegistroPorId(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de empleado o actualiza uno existente.
     *
     * @param empleadoDTO el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     */
    @Override
    @Transactional
    public EmpleadoDTO guardarRegistro(EmpleadoDTO empleadoDTO) {
        Usuario usuario = convertirUsuarioAEntidad(empleadoDTO);
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        Empleado empleado = convertirEmpleadoAEntidad(empleadoDTO);
        empleado.setUsuario(usuarioGuardado);

        Empleado empleadoGuardado = empleadoRepositorio.save(empleado);
        return convertirADTO(empleadoGuardado);
    }

    /**
     * Elimina un registro de Empleado.
     *
     * @param idEmpleado el empleado a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Integer idEmpleado) {
        empleadoRepositorio.deleteById(idEmpleado);
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
        usuario.setEsEmpleado(usuarioDTO.getEsEmpleado());
        usuario.setEsEmpleado(usuarioDTO.getEsEmpleado());
        return usuario;
    }

    private Empleado convertirEmpleadoAEntidad(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setCargo(empleadoDTO.getCargo());
        empleado.setSueldo(empleadoDTO.getSueldo());
        return empleado;
    }

    private EmpleadoDTO convertirADTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setUsername(empleado.getUsername());
        dto.setPassword(empleado.getPassword());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setIdentificacion(empleado.getIdentificacion());
        dto.setTelefono(empleado.getTelefono());
        dto.setEmail(empleado.getEmail());
        dto.setEsEmpleado(empleado.getEsEmpleado());
        dto.setEsEmpleado(empleado.getEsEmpleado());
        dto.setCargo(empleado.getCargo());
        dto.setSueldo(empleado.getSueldo());
        return dto;
    }
}

