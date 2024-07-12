package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.dto.PacienteDTO;

import java.util.List;

/**
 * Interfaz para definir los métodos de servicio relacionados con la entidad Paciente.
 */
public interface IPacienteServicio {

    /**
     * Lista todos los registros de pacientes.
     *
     * @return una lista de todos los pacientes.
     */
    List<PacienteDTO> listarRegistros();

    /**
     * Busca un registro de paciente por su ID.
     *
     * @param idPaciente el ID del paciente.
     * @return el paciente con el ID especificado, o null si no se encuentra.
     */
    PacienteDTO buscarRegistroPorId(Integer idPaciente);

    /**
     * Guarda un nuevo registro de paciente o actualiza uno existente.
     *
     * @param pacienteDTO el paciente a guardar o actualizar.
     * @return el paciente guardado o actualizado.
     */
    PacienteDTO guardarRegistro(PacienteDTO pacienteDTO);

    /**
     * Elimina un registro de paciente.
     *
     * @param idPaciente el ID del paciente a eliminar.
     */
    void eliminarRegistro(Integer idPaciente);
}



