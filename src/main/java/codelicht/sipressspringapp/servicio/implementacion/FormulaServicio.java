package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Formula;
import codelicht.sipressspringapp.repositorio.FormulaRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IFormulaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Formula.
 */
@Service
public class FormulaServicio implements IFormulaServicio {

    @Autowired
    private FormulaRepositorio formulaRepositorio;

    @Override
    public List<Formula> listarFormulas() {
        return formulaRepositorio.findAll();
    }

    @Override
    public Formula buscarFormulaPorId(Integer idFormula) {
        return formulaRepositorio.findById(idFormula).orElse(null);
    }

    @Override
    public Formula guardarFormula(Formula formula) {
        return formulaRepositorio.save(formula);
    }

    @Override
    public void eliminarFormula(Formula formula) {
        formulaRepositorio.delete(formula);
    }
}
