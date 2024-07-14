package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.PacienteDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad PacienteDTO.
 */
public interface IPacienteServicio {

    /**
     * Obtiene todos los registros Paciente.
     *
     * @return una lista de todos los registros Paciente.
     */
    List<PacienteDTO> listarRegistros();

    /**
     * Busca un registro Paciente por su ID.
     *
     * @param idPacienteDTO el ID del registro Paciente.
     * @return registro Paciente con el ID especificado, o null si no se encuentra.
     */
    PacienteDTO buscarRegistroPorId(Integer idPacienteDTO);

    /**
     * Guarda un nuevo registro Paciente o actualiza uno existente.
     *
     * @param pacienteDTO el registro Paciente a guardar o actualizar.
     * @return registro Paciente guardado o actualizado.
     */
    PacienteDTO guardarRegistro(PacienteDTO pacienteDTO);

    /**
     * Elimina un registro de Paciente.
     *
     * @param idPaciente el ID del registro Paciente a eliminar.
     */
    void eliminarRegistro(Integer idPaciente);
}



