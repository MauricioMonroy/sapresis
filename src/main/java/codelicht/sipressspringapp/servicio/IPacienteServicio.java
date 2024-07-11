package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Paciente;

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
    List<Paciente> listarRegistros();

    /**
     * Busca un registro de paciente por su ID.
     *
     * @param idPaciente el ID del paciente.
     * @return el paciente con el ID especificado, o null si no se encuentra.
     */
    Paciente buscarRegistroPorId(Integer idPaciente);

    /**
     * Guarda un nuevo registro de paciente o actualiza uno existente.
     *
     * @param paciente el paciente a guardar o actualizar.
     * @return el paciente guardado o actualizado.
     */
    Paciente guardarRegistro(Paciente paciente);

    /**
     * Elimina un registro de paciente.
     *
     * @param paciente el paciente a eliminar.
     */
    void eliminarRegistro(Paciente paciente);
}


