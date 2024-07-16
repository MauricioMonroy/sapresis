package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Formula;
import codelicht.sipressspringapp.servicio.interfaces.IFormulaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class FormulaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FormulaControlador.class);

    @Autowired
    private IFormulaServicio formulaServicio;

    // http://localhost:8080/sipress-app/formulas
    @GetMapping("/formulas")
    public List<Formula> obtenerFormulas() {
        var formulas = formulaServicio.listarFormulas();
        formulas.forEach((formula -> logger.info(formula.toString())));
        return formulas;
    }

    @PostMapping("/formulas")
    public Formula agregarFormula(@RequestBody Formula formula) {
        logger.info("Formula a agregar: " + formula);
        return formulaServicio.guardarFormula(formula);
    }
}
