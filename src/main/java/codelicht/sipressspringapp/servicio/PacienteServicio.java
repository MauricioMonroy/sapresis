package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.PacienteDTO;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la entidad Paciente.
 */
@Service
public class PacienteServicio implements IPacienteServicio {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Override
    public List<PacienteDTO> listarRegistros() {
        return pacienteRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDTO buscarRegistroPorId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public PacienteDTO guardarRegistro(PacienteDTO pacienteDTO) {
        Paciente paciente = convertirAEntidad(pacienteDTO);
        Paciente pacienteGuardado = pacienteRepositorio.save(paciente);
        return convertirADTO(pacienteGuardado);
    }

    @Override
    public void eliminarRegistro(Integer idPaciente) {
        pacienteRepositorio.deleteById(idPaciente);
    }

    private PacienteDTO convertirADTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        // mapea los campos de Usuario
        dto.setId(paciente.getId());
        dto.setUsername(paciente.getUsername());
        dto.setPassword(paciente.getPassword());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setIdentificacion(paciente.getIdentificacion());
        dto.setTelefono(paciente.getTelefono());
        dto.setEmail(paciente.getEmail());
        dto.setEsPaciente(paciente.getEsPaciente());
        dto.setEsEmpleado(paciente.getEsEmpleado());
        // mapea los campos específicos de Paciente
        dto.setDetalleEps(paciente.getDetalleEps());
        dto.setFechaConsulta(paciente.getFechaConsulta());
        return dto;
    }

    private Paciente convertirAEntidad(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        // mapea los campos de Usuario
        paciente.setId(dto.getId());
        paciente.setUsername(dto.getUsername());
        paciente.setPassword(dto.getPassword());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setIdentificacion(dto.getIdentificacion());
        paciente.setTelefono(dto.getTelefono());
        paciente.setEmail(dto.getEmail());
        paciente.setEsPaciente(dto.getEsPaciente());
        paciente.setEsEmpleado(dto.getEsEmpleado());
        // mapea los campos específicos de Paciente
        paciente.setDetalleEps(dto.getDetalleEps());
        paciente.setFechaConsulta(dto.getFechaConsulta());
        return paciente;
    }
}


