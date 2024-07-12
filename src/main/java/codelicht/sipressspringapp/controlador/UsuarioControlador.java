package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.UsuarioDTO;
import codelicht.sipressspringapp.servicio.IUsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sipress-spring-app")
@CrossOrigin(value = "http://localhost:3000")
public class UsuarioControlador {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        var usuarios = usuarioServicio.listarRegistros();
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
        return usuarios;
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioDTO buscarUsuarioPorId(@PathVariable Integer id) {
        return usuarioServicio.buscarRegistroPorId(id);
    }

    @PostMapping("/usuarios")
    public UsuarioDTO guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioServicio.guardarRegistro(usuarioDTO);
    }

    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioServicio.eliminarRegistro(id);
    }
}

