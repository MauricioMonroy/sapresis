package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "id_usuario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Empleado extends Usuario{

    // Campo cargo con una longitud máxima de 45 caracteres
    @Column(name = "cargo", length = 45)
    private String cargo;

    // Campo sueldo para almacenar el salario del empleado
    @Column(name = "sueldo")
    private Double sueldo;

}
