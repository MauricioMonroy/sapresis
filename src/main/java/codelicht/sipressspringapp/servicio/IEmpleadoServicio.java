package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.EmpleadoDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Empleado.
 */
public interface IEmpleadoServicio {

    /**
     * Lista todos los registros Empleado.
     *
     * @return una lista de todos los registros Empleado.
     */
    List<EmpleadoDTO> listarRegistros();

    /**
     * Busca un registro Empleado por su ID.
     *
     * @param idEmpleadoDTO el ID del Empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    EmpleadoDTO buscarRegistroPorId(Integer idEmpleadoDTO);

    /**
     * Guarda un nuevo registro Empleado o actualiza uno existente.
     *
     * @param empleadoDTO el registro Empleado a guardar o actualizar.
     * @return registro Empleado guardado o actualizado.
     */
    EmpleadoDTO guardarRegistro(EmpleadoDTO empleadoDTO);

    /**
     * Elimina un registro de Empleado.
     *
     * @param idEmpleado el empleado a eliminar.
     */
    void eliminarRegistro(Integer idEmpleado);
}

