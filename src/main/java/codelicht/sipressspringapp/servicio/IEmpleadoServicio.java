package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Empleado;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Empleado.
 */
public interface IEmpleadoServicio {

    /**
     * Lista todos los registros de empleados.
     *
     * @return una lista de todos los empleados.
     */
    List<Empleado> listarRegistros();

    /**
     * Busca un registro de empleado por su ID.
     *
     * @param idEmpleado el ID del empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    Empleado buscarRegistroPorId(Integer idEmpleado);

    /**
     * Guarda un nuevo registro de empleado o actualiza uno existente.
     *
     * @param empleado el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     */
    Empleado guardarRegistro(Empleado empleado);

    /**
     * Elimina un registro de empleado.
     *
     * @param empleado el empleado a eliminar.
     */
    void eliminarRegistro(Empleado empleado);
}

