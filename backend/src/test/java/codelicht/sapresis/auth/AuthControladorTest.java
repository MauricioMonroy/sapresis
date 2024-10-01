package codelicht.sapresis.auth;

import codelicht.sapresis.auth.servicio.JwtService;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de pruebas para el controlador de autenticación
 * Implementa pruebas para el inicio de sesión con credenciales correctas e incorrectas
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Setter
    @Getter
    @Autowired
    private JwtService jwtService;

    @Test
    public void loginExitosoTest() throws Exception {
        // Implementar el test para el endpoint de autenticación con credenciales correctas
        String loginRequest =
                "{ \"email\": \"super.admin@correo.com\", " +
                        "\"password\": \"G)T,T_Yr8]c6:YM\" }";

        mockMvc.perform(post("http://localhost:8080/sapresis/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.token")
                        .exists());
    }

    @Test
    public void loginFallidoTest() throws Exception {
        // Implementar el test para el endpoint de autenticación con credenciales incorrectas
        String loginRequest =
                "{ \"email\": \"user@correo.com\", " +
                        "\"password\": \"wrongPassword\" }";

        mockMvc.perform(post("http://localhost:8080/sapresis/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status()
                        .isUnauthorized());
    }

}
