package codelicht.sipresswebapp.servicio.interfaces;

import codelicht.sipresswebapp.modelo.Formula;

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
