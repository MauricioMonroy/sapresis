package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Formula;

import java.util.List;

/**
 * Interface para la entidad Fórmula.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IFormulaServicio {
    List<Formula> listarFormulas();

    Formula buscarFormulaPorId(Integer idFormula);

    Formula guardarFormula(Formula formula);

    void eliminarFormula(Formula formula);
}
