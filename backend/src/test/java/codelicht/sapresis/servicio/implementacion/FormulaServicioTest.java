package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Formula;
import codelicht.sapresis.repositorio.FormulaRepositorio;
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
 * Clase de pruebas para el servicio de Formula.
 * Utiliza Mockito para simular el repositorio de Formula.
 * Se prueban los métodos CRUD del servicio.
 */

class FormulaServicioTest {

    @Mock
    private FormulaRepositorio formulaRepositorio;  // Simula el repositorio de Formula

    @InjectMocks
    private FormulaServicio formulaServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Formula
    @Test
    public void testListarFormulas() {
        Formula formula1 = new Formula();
        Formula formula2 = new Formula();
        when(formulaRepositorio.findAll()).thenReturn(Arrays.asList(formula1, formula2));

        List<Formula> formulas = formulaServicio.listarFormulas();

        assertEquals(2, formulas.size());
        verify(formulaRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Formula por su ID
    @Test
    public void testBuscarFormulaPorId() {
        Formula formula = new Formula();
        when(formulaRepositorio.findById(1)).thenReturn(Optional.of(formula));

        Formula resultado = formulaServicio.buscarFormulaPorId(1);

        assertNotNull(resultado);
        verify(formulaRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Formula
    @Test
    public void testGuardarFormula() {
        Formula formula = new Formula();
        when(formulaRepositorio.save(formula)).thenReturn(formula);

        Formula resultado = formulaServicio.guardarFormula(formula);

        assertEquals(formula, resultado);
        verify(formulaRepositorio, times(1)).save(formula);
    }

    // Prueba para eliminar un Formula
    @Test
    public void testEliminarFormula() {
        Formula formula = new Formula();

        formulaServicio.eliminarFormula(formula);

        verify(formulaRepositorio, times(1)).delete(formula);
    }
}