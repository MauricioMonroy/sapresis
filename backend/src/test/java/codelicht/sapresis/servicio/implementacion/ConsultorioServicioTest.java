package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Consultorio;
import codelicht.sapresis.repositorio.ConsultorioRepositorio;
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
 * Clase de pruebas para el servicio de Consultorio.
 * Utiliza Mockito para simular el repositorio de Consultorio.
 * Se prueban los métodos CRUD del servicio.
 */

class ConsultorioServicioTest {

    @Mock
    private ConsultorioRepositorio consultorioRepositorio;  // Simula el repositorio de Consultorio

    @InjectMocks
    private ConsultorioServicio consultorioServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Consultorio
    @Test
    public void testListarConsultorios() {
        Consultorio consultorio1 = new Consultorio();
        Consultorio consultorio2 = new Consultorio();
        when(consultorioRepositorio.findAll()).thenReturn(Arrays.asList(consultorio1, consultorio2));

        List<Consultorio> consultorios = consultorioServicio.listarConsultorios();

        assertEquals(2, consultorios.size());
        verify(consultorioRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Consultorio por su ID
    @Test
    public void testBuscarConsultorioPorId() {
        Consultorio consultorio = new Consultorio();
        when(consultorioRepositorio.findById(1)).thenReturn(Optional.of(consultorio));

        Consultorio resultado = consultorioServicio.buscarConsultorioPorId(1);

        assertNotNull(resultado);
        verify(consultorioRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Consultorio
    @Test
    public void testGuardarConsultorio() {
        Consultorio consultorio = new Consultorio();
        when(consultorioRepositorio.save(consultorio)).thenReturn(consultorio);

        Consultorio resultado = consultorioServicio.guardarConsultorio(consultorio);

        assertEquals(consultorio, resultado);
        verify(consultorioRepositorio, times(1)).save(consultorio);
    }

    // Prueba para eliminar un Consultorio
    @Test
    public void testEliminarConsultorio() {
        Consultorio consultorio = new Consultorio();

        consultorioServicio.eliminarConsultorio(consultorio);

        verify(consultorioRepositorio, times(1)).delete(consultorio);
    }
}