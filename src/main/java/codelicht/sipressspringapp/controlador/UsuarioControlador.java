package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Usuario;
import codelicht.sipressspringapp.servicio.IUsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sipress-spring-app")
@CrossOrigin(value = "http://localhost:3000")
public class UsuarioControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private IUsuarioServicio usuarioServicio;

    // http://localhost:8080/sipress-spring-app/usuarios
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        var usuarios = usuarioServicio.listarRegistros();
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }
}
