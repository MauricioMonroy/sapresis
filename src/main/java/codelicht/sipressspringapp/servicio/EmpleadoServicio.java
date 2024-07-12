package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio para la entidad Empleado.
 */
@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    /**
     * Lista todos los registros de empleados.
     *
     * @return una lista de todos los empleados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarRegistros() {
        return empleadoRepositorio.findAll();
    }

    /**
     * Busca un registro de empleado por su ID.
     *
     * @param idEmpleado el ID del empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Empleado buscarRegistroPorId(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado).orElse(null);
    }

    /**
     * Guarda un nuevo registro de empleado o actualiza uno existente.
     *
     * @param empleado el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     */
    @Override
    @Transactional
    public Empleado guardarRegistro(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    /**
     * Elimina un registro de empleado.
     *
     * @param empleado el empleado a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}

