package codelicht.sipressspringapp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    // Anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    // Campo username con una longitud máxima de 45 caracteres
    @Column(name = "username", length = 45)
    private String username;

    // Campo password con una longitud máxima de 45 caracteres
    @Column(name = "password", length = 45)
    private String password;

    // Campo nombre mapeado a la columna nombre en la base de datos
    @Column(name = "nombre")
    private String nombre;

    // Campo apellido mapeado a la columna apellido en la base de datos
    @Column(name = "apellido")
    private String apellido;

    // Campo identificacion mapeado a la columna identificacion en la base de datos
    @Column(name = "identificacion")
    private String identificacion;

    // Campo telefono mapeado a la columna telefono en la base de datos
    @Column(name = "telefono")
    private String telefono;

    // Campo email mapeado a la columna email en la base de datos
    @Column(name = "email")
    private String email;

    // Campo esPaciente indica si el usuario es un paciente
    @Column(name = "es_paciente")
    private Boolean esPaciente;

    // Campo esEmpleado indica si el usuario es un empleado
    @Column(name = "es_empleado")
    private Boolean esEmpleado;
}
