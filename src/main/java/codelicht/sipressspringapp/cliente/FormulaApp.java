package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Formula;
import codelicht.sipressspringapp.modelo.Paciente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FormulaApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las fórmulas");
            System.out.println("2. Buscar formula por su número");
            System.out.println("3. Guardar una nueva fórmula");
            System.out.println("4. Eliminar una fórmula");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarFormulas();
                    break;
                case 2:
                    buscarFormulaPorId();
                    break;
                case 3:
                    guardarFormula();
                    break;
                case 4:
                    eliminarFormula();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarFormulas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/formulas"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Formula> formulas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            formulas.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarFormulaPorId() {
        try {
            System.out.println("Ingrese el número de la fórmula:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/formulas/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Formula formula = mapper.readValue(response.body(), Formula.class);
            System.out.println(formula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarFormula() {
        try {
            Formula formula = new Formula();
            System.out.println("Ingrese el número de la fórmula:");
            formula.setNumeroFormula(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID del paciente y asignarlo a la fórmula
            System.out.println("Ingrese el ID del paciente asociado a la fórmula:");
            int pacienteId = scanner.nextInt();
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(pacienteId);
            formula.setPaciente(paciente);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre de la medicación:");
            formula.setNombreMedicacion(scanner.nextLine());

            System.out.println("Ingrese la fecha de la medicación (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            Date fechaMedicacion = dateFormat.parse(fechaInput);
            formula.setFechaMedicacion(fechaMedicacion);

            System.out.println("Ingrese el costo de la medicación:");
            formula.setCostoMedicacion(scanner.nextDouble());

            // Convertir el objeto formula a JSON
            String requestBody = mapper.writeValueAsString(formula);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Formula nuevaFormula = mapper.readValue(response.body(), Formula.class);
            System.out.println("Fórmula guardada: " + nuevaFormula);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarFormula() {
        try {
            System.out.println("Ingrese el número de la formula a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/formulas/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Formula eliminada.");
            } else {
                System.out.println("Error al eliminar la formula. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
