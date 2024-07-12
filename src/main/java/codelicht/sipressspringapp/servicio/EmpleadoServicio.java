package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<EmpleadoDTO> listarRegistros() {
        return empleadoRepositorio.findAll().stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de empleado por su ID.
     *
     * @param idEmpleado el ID del empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO buscarRegistroPorId(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado)
                .map(this::convertirEntidadADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de empleado o actualiza uno existente.
     *
     * @param empleadoDTO el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     */
    @Override
    @Transactional
    public EmpleadoDTO guardarRegistro(EmpleadoDTO empleadoDTO) {
        Empleado empleado = convertirDTOAEntidad(empleadoDTO);
        Empleado empleadoGuardado = empleadoRepositorio.save(empleado);
        return convertirEntidadADTO(empleadoGuardado);
    }

    /**
     * Elimina un registro de empleado.
     *
     * @param idEmpleado el empleado a eliminar.
     */
    @Override
    @Transactional
    public void eliminarRegistro(Integer idEmpleado) {
        empleadoRepositorio.deleteById(idEmpleado);
    }

    private EmpleadoDTO convertirEntidadADTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setSueldo(empleado.getSueldo());
        return empleadoDTO;
    }

    private Empleado convertirDTOAEntidad(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setId(empleadoDTO.getId());
        empleado.setCargo(empleadoDTO.getCargo());
        empleado.setSueldo(empleadoDTO.getSueldo());
        return empleado;
    }
}

