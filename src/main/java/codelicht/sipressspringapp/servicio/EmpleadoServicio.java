package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.EmpleadoDTO;
import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<EmpleadoDTO> listarRegistros() {
        return empleadoRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un registro de empleado por su ID.
     *
     * @param idEmpleado el ID del empleado.
     * @return el empleado con el ID especificado, o null si no se encuentra.
     */
    @Override
    public EmpleadoDTO buscarRegistroPorId(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado)
                .map(this::convertirADTO)
                .orElse(null);
    }

    /**
     * Guarda un nuevo registro de empleado o actualiza uno existente.
     *
     * @param empleadoDTO el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     */
    @Override
    public EmpleadoDTO guardarRegistro(EmpleadoDTO empleadoDTO) {
        Empleado empleado = convertirAEntidad(empleadoDTO);
        Empleado empleadoGuardado = empleadoRepositorio.save(empleado);
        return convertirADTO(empleadoGuardado);
    }

    /**
     * Elimina un registro de empleado.
     *
     * @param idEmpleado el empleado a eliminar.
     */
    @Override
    public void eliminarRegistro(Integer idEmpleado) {
        empleadoRepositorio.deleteById(idEmpleado);
    }

    private EmpleadoDTO convertirADTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        // mapea los campos de Usuario
        dto.setId(empleado.getId());
        dto.setUsername(empleado.getUsername());
        dto.setPassword(empleado.getPassword());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setIdentificacion(empleado.getIdentificacion());
        dto.setTelefono(empleado.getTelefono());
        dto.setEmail(empleado.getEmail());
        dto.setEsEmpleado(empleado.getEsEmpleado());
        dto.setEsEmpleado(empleado.getEsEmpleado());
        // mapea los campos específicos de Empleado
        dto.setCargo(empleado.getCargo());
        dto.setSueldo(empleado.getSueldo());
        return dto;
    }

    private Empleado convertirAEntidad(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        // mapea los campos de Usuario
        empleado.setId(dto.getId());
        empleado.setUsername(dto.getUsername());
        empleado.setPassword(dto.getPassword());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setIdentificacion(dto.getIdentificacion());
        empleado.setTelefono(dto.getTelefono());
        empleado.setEmail(dto.getEmail());
        empleado.setEsEmpleado(dto.getEsEmpleado());
        empleado.setEsEmpleado(dto.getEsEmpleado());
        // mapea los campos específicos de Empleado
        empleado.setCargo(dto.getCargo());
        empleado.setSueldo(dto.getSueldo());
        return empleado;
    }
}

