package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Formula;

import java.util.List;

public interface IFormulaServicio {
    public List<Formula> listarFormulas();

    public Formula buscarFormulaPorId(Integer idFormula);

    public Formula guardarFormula(Formula formula);

    public void eliminarFormula(Formula formula);
}
