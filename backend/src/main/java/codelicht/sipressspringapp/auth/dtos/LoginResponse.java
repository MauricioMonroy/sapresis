package codelicht.sipressspringapp.auth.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la respuesta del login
 * Realiza la gestión de la autenticación y el tiempo de expiración del token
 */
@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }
}
