package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<UsuarioDTO> listarRegistros() {
        return usuarioRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarRegistroPorId(Integer idUsuario) {
        return usuarioRepositorio.findById(idUsuario)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public UsuarioDTO guardarRegistro(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertirAEntidad(usuarioDTO);
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);
        return convertirADTO(usuarioGuardado);
    }

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
