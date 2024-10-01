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
 * Clase de pruebas para el controlador de usuarios
 * Implementa pruebas para el registro de usuarios con datos correctos e incorrectos
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Setter
    @Getter
    @Autowired
    private JwtService jwtService;

    @Test
    public void registroExitosoTest() throws Exception {
        // Implementar el test para el endpoint de registro de usuarios
        String registrationRequest =
                "{ \"email\": \"usuario@prueba.com\", " +
                        "\"password\": \"password\", " +
                        "\"nombreCompleto\": \"Usuario Prueba\" }";
        mockMvc.perform(post(
                        "http://localhost:8080/sapresis/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registrationRequest))
                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.message")
                        .value("Usuario registrado exitosamente"));
    }

    @Test
    public void registroFallidoTest() throws Exception {
        // Implementar el test para el endpoint de registro de usuarios
        String registrationRequest =
                "{ \"email\": \"\", " +
                        "\"password\": \"\", " +
                        "\"nombreCompleto\": \"\" }";
        mockMvc.perform(post(
                        "http://localhost:8080/sapresis/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registrationRequest))
                .andExpect(status()
                        .isBadRequest());
    }
}
