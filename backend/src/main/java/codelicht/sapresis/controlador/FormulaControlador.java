package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Formula;
import codelicht.sapresis.modelo.Paciente;
import codelicht.sapresis.servicio.interfaces.IFormulaServicio;
import codelicht.sapresis.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sapresis")
@CrossOrigin(value = "http://localhost:3000")
public class FormulaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FormulaControlador.class);

    private final IFormulaServicio formulaServicio;
    private final IPacienteServicio pacienteServicio;

    // Constructor para inyección de dependencias
    public FormulaControlador(IFormulaServicio formulaServicio, IPacienteServicio pacienteServicio) {
        this.formulaServicio = formulaServicio;
        this.pacienteServicio = pacienteServicio;
    }

    // http://localhost:8080/sapresis/formulas
    @GetMapping("/formulas")
        @PreAuthorize("isAuthenticated()")
    public List<Formula> obtenerFormulas() {
        var formulas = formulaServicio.listarFormulas();
        formulas.forEach((formula -> logger.info(formula.toString())));
        return formulas;
    }

    @GetMapping("/formulas/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<Formula> buscarFormulaPorId(@PathVariable Integer id) {
        Formula formula = formulaServicio.buscarFormulaPorId(id);
        if (formula == null)
            throw new RecursoNoEncontradoExcepcion("Formula no encontrada con el número: " + id);
        return ResponseEntity.ok(formula);
    }

    @PostMapping("/formulas")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<?> agregarFormula(@Valid @RequestBody Formula formula, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Formula a agregar: {}", formula);
        if (formula.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(formula.getPaciente().getIdPaciente());
            formula.setPaciente(paciente);
        }
        Formula nuevaFormula = formulaServicio.guardarFormula(formula);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaFormula.getNumeroFormula())
                .toUri();
        return ResponseEntity.created(location).body(nuevaFormula);
    }

    @PutMapping("/formulas/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN, ADMIN')")
    public ResponseEntity<Formula> actualizarFormula(@PathVariable Integer id,
                                                     @RequestBody Formula formulaRecuperada) {
        Formula formula = formulaServicio.buscarFormulaPorId(id);
        if (formula == null)
            throw new RecursoNoEncontradoExcepcion("Formula no encontrada con el número: " + id);
        formula.setNumeroFormula(formulaRecuperada.getNumeroFormula());
        formula.setPaciente(formulaRecuperada.getPaciente());
        formula.setNombreMedicacion(formulaRecuperada.getNombreMedicacion());
        formula.setFechaMedicacion(formulaRecuperada.getFechaMedicacion());
        formula.setCostoMedicacion(formulaRecuperada.getCostoMedicacion());
        formulaServicio.guardarFormula(formula);
        return ResponseEntity.ok(formula);
    }

    @DeleteMapping("/formulas/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminarFormula(@PathVariable Integer id) {
        Formula formula = formulaServicio.buscarFormulaPorId(id);
        if (formula == null)
            throw new RecursoNoEncontradoExcepcion("Formula no encontrada con el número: " + id);
        formulaServicio.eliminarFormula(formula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
