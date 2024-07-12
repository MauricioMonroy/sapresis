package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {

    // Anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    @Column(name = "id_empleado", nullable = false)
    private Integer id;

    // Anotación @ManyToOne indica una relación de muchos a uno con la entidad Usuario
    // fetch = FetchType.LAZY especifica que la relación se cargará cuando se acceda a ella por primera vez
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    // Campo cargo con una longitud máxima de 45 caracteres
    @Column(name = "cargo", length = 45)
    private String cargo;

    // Campo sueldo para almacenar el salario del empleado
    @Column(name = "sueldo")
    private Double sueldo;

    // Sobrescribimos el método toString para evitar la carga ´lazy´
    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", cargo='" + cargo + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
