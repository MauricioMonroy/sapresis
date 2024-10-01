package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Consulta;
import codelicht.sapresis.repositorio.ConsultaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para el servicio de Consulta.
 * Utiliza Mockito para simular el repositorio de Consulta.
 * Se prueban los métodos CRUD del servicio.
 */

class ConsultaServicioTest {

    @Mock
    private ConsultaRepositorio consultaRepositorio;  // Simula el repositorio de Consulta

    @InjectMocks
    private ConsultaServicio consultaServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Consulta
    @Test
    void testListarConsultas() {
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        when(consultaRepositorio.findAll()).thenReturn(Arrays.asList(consulta1, consulta2));

        List<Consulta> consultas = consultaServicio.listarConsultas();

        assertEquals(2, consultas.size());
        verify(consultaRepositorio, times(1)).findAll();
    }

    // Prueba para buscar una Consulta por su ID de Paciente
    @Test
    void testBuscarConsultaPorIdPaciente() {
        Consulta consulta = new Consulta();
        when(consultaRepositorio.findByConsultaPK_PacienteId(1)).thenReturn(List.of(consulta));

        Consulta resultado = consultaServicio.buscarConsultaPorIdPaciente(1).get(0);

        assertNotNull(resultado);
        verify(consultaRepositorio, times(1)).findByConsultaPK_PacienteId(1);
    }

    // Prueba para buscar una Consulta por su ID de Doctor
    @Test
    void testBuscarConsultaPorIdDoctor() {
        Consulta consulta = new Consulta();
        when(consultaRepositorio.findByConsultaPK_DoctorId(1)).thenReturn(List.of(consulta));

        Consulta resultado = consultaServicio.buscarConsultaPorIdDoctor(1).get(0);

        assertNotNull(resultado);
        verify(consultaRepositorio, times(1)).findByConsultaPK_DoctorId(1);
    }

    // Prueba para guardar una Consulta
    @Test
    void testGuardarConsulta() {
        Consulta consulta = new Consulta();
        when(consultaRepositorio.save(consulta)).thenReturn(consulta);

        Consulta resultado = consultaServicio.guardarConsulta(consulta);

        assertEquals(consulta, resultado);
        verify(consultaRepositorio, times(1)).save(consulta);
    }

    // Prueba para eliminar una Consulta
    @Test
    void testEliminarConsulta() {
        Consulta consulta = new Consulta();

        consultaServicio.eliminarConsulta(consulta);

        verify(consultaRepositorio, times(1)).delete(consulta);
    }
}