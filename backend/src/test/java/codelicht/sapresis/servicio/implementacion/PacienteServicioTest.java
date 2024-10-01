package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Paciente;
import codelicht.sapresis.repositorio.PacienteRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para el servicio de Paciente.
 * Utiliza Mockito para simular el repositorio de Paciente.
 * Se prueban los métodos CRUD del servicio.
 */

class PacienteServicioTest {

    @Mock
    private PacienteRepositorio pacienteRepositorio;  // Simula el repositorio de Paciente

    @InjectMocks
    private PacienteServicio pacienteServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Paciente
    @Test
    public void testListarPacientes() {
        Paciente paciente1 = new Paciente();
        Paciente paciente2 = new Paciente();
        when(pacienteRepositorio.findAll()).thenReturn(Arrays.asList(paciente1, paciente2));

        List<Paciente> pacientes = pacienteServicio.listarPacientes();

        assertEquals(2, pacientes.size());
        verify(pacienteRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Paciente por su ID
    @Test
    public void testBuscarPacientePorId() {
        Paciente paciente = new Paciente();
        when(pacienteRepositorio.findById(1)).thenReturn(Optional.of(paciente));

        Paciente resultado = pacienteServicio.buscarPacientePorId(1);

        assertNotNull(resultado);
        verify(pacienteRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Paciente
    @Test
    public void testGuardarPaciente() {
        Paciente paciente = new Paciente();
        when(pacienteRepositorio.save(paciente)).thenReturn(paciente);

        Paciente resultado = pacienteServicio.guardarPaciente(paciente);

        assertEquals(paciente, resultado);
        verify(pacienteRepositorio, times(1)).save(paciente);
    }

    // Prueba para eliminar un Paciente
    @Test
    public void testEliminarPaciente() {
        Paciente paciente = new Paciente();

        pacienteServicio.eliminarPaciente(paciente);

        verify(pacienteRepositorio, times(1)).delete(paciente);
    }
}