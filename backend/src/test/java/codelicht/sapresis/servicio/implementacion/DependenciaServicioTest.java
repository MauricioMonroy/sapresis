package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Dependencia;
import codelicht.sapresis.repositorio.DependenciaRepositorio;
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
 * Clase de pruebas para el servicio de Dependencia.
 * Utiliza Mockito para simular el repositorio de Dependencia.
 * Se prueban los métodos CRUD del servicio.
 */

class DependenciaServicioTest {

    @Mock
    private DependenciaRepositorio dependenciaRepositorio;  // Simula el repositorio de Dependencia

    @InjectMocks
    private DependenciaServicio dependenciaServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Dependencia
    @Test
    public void testListarDependencias() {
        Dependencia dependencia1 = new Dependencia();
        Dependencia dependencia2 = new Dependencia();
        when(dependenciaRepositorio.findAll()).thenReturn(Arrays.asList(dependencia1, dependencia2));

        List<Dependencia> dependencias = dependenciaServicio.listarDependencias();

        assertEquals(2, dependencias.size());
        verify(dependenciaRepositorio, times(1)).findAll();
    }

    // Prueba para buscar una Dependencia por su ID
    @Test
    public void testBuscarDependenciaPorId() {
        Dependencia dependencia = new Dependencia();
        when(dependenciaRepositorio.findById(1)).thenReturn(Optional.of(dependencia));

        Dependencia resultado = dependenciaServicio.buscarDependenciaPorId(1);

        assertNotNull(resultado);
        verify(dependenciaRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar una Dependencia
    @Test
    public void testGuardarDependencia() {
        Dependencia dependencia = new Dependencia();
        when(dependenciaRepositorio.save(dependencia)).thenReturn(dependencia);

        Dependencia resultado = dependenciaServicio.guardarDependencia(dependencia);

        assertEquals(dependencia, resultado);
        verify(dependenciaRepositorio, times(1)).save(dependencia);
    }

    // Prueba para eliminar una Dependencia
    @Test
    public void testEliminarDependencia() {
        Dependencia dependencia = new Dependencia();

        dependenciaServicio.eliminarDependencia(dependencia);

        verify(dependenciaRepositorio, times(1)).delete(dependencia);
    }
}