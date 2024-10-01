package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Eps;
import codelicht.sapresis.repositorio.EpsRepositorio;
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
 * Clase de pruebas para el servicio de Eps.
 * Utiliza Mockito para simular el repositorio de Eps.
 * Se prueban los métodos CRUD del servicio.
 */

class EpsServicioTest {

    @Mock
    private EpsRepositorio epsRepositorio;  // Simula el repositorio de Eps

    @InjectMocks
    private EpsServicio epsServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Eps
    @Test
    public void testListarEpsS() {
        Eps eps1 = new Eps();
        Eps eps2 = new Eps();
        when(epsRepositorio.findAll()).thenReturn(Arrays.asList(eps1, eps2));

        List<Eps> epsS = epsServicio.listarEpsS();

        assertEquals(2, epsS.size());
        verify(epsRepositorio, times(1)).findAll();
    }

    // Prueba para buscar una Eps por su ID
    @Test
    public void testBuscarEpsPorId() {
        Eps eps = new Eps();
        when(epsRepositorio.findById(1)).thenReturn(Optional.of(eps));

        Eps resultado = epsServicio.buscarEpsPorId(1);

        assertNotNull(resultado);
        verify(epsRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar una Eps
    @Test
    public void testGuardarEps() {
        Eps eps = new Eps();
        when(epsRepositorio.save(eps)).thenReturn(eps);

        Eps resultado = epsServicio.guardarEps(eps);

        assertEquals(eps, resultado);
        verify(epsRepositorio, times(1)).save(eps);
    }

    // Prueba para eliminar una Eps
    @Test
    public void testEliminarEps() {
        Eps eps = new Eps();

        epsServicio.eliminarEps(eps);

        verify(epsRepositorio, times(1)).delete(eps);
    }
}