package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Formula;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IFormulaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class FormulaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FormulaControlador.class);

    @Autowired
    private IFormulaServicio formulaServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    // http://localhost:8080/sipress-app/formulas
    @GetMapping("/formulas")
    public List<Formula> obtenerFormulas() {
        var formulas = formulaServicio.listarFormulas();
        formulas.forEach((formula -> logger.info(formula.toString())));
        return formulas;
    }

    @GetMapping("/formulas/{id}")
    public Formula buscarFormulaPorId(@PathVariable Integer id) {
        return formulaServicio.buscarFormulaPorId(id);
    }

    @PostMapping("/formulas")
    public ResponseEntity<?> agregarFormula(@Valid @RequestBody Formula formula, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Formula a agregar: " + formula);
        if (formula.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(formula.getPaciente().getIdPaciente());
            formula.setPaciente(paciente);
        }
        Formula nuevaFormula = formulaServicio.guardarFormula(formula);
        return ResponseEntity.ok(nuevaFormula);
    }

    @DeleteMapping("/formulas/{id}")
    public ResponseEntity<Void> eliminarFormula(@PathVariable("id") Integer id) {
        Formula formula = formulaServicio.buscarFormulaPorId(id);
        if (formula != null) {
            formulaServicio.eliminarFormula(formula);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
