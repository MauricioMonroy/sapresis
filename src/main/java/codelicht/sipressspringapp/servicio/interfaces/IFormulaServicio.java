package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Formula;

import java.util.List;

/**
 * Interface para la entidad Formula.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IFormulaServicio {
    public List<Formula> listarFormulas();

    public Formula buscarFormulaPorId(Integer idFormula);

    public Formula guardarFormula(Formula formula);

    public void eliminarFormula(Formula formula);
}
