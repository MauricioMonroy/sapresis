package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    List<Empleado> listarRegistros();

    Empleado buscarRegistroPorId(Integer idEmpleado);

    Empleado guardarRegistro(Empleado empleado);

    void eliminarRegistro(Empleado empleado);
}
