package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Eps;
import codelicht.sipressspringapp.modelo.Paciente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class PacienteApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los pacientes");
            System.out.println("2. Buscar paciente por ID");
            System.out.println("3. Guardar un nuevo paciente");
            System.out.println("4. Eliminar un paciente");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarPacientes();
                    break;
                case 2:
                    buscarPacientePorId();
                    break;
                case 3:
                    guardarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarPacientes() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/pacientes"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Paciente> pacientes = mapper.readValue(response.body(), new TypeReference<>() {
            });
            pacientes.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarPacientePorId() {
        try {
            System.out.println("Ingrese el ID del paciente:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/pacientes/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Paciente paciente = mapper.readValue(response.body(), Paciente.class);
            System.out.println(paciente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarPaciente() {
        try {
            Paciente paciente = new Paciente();

            System.out.println("Ingrese el ID del paciente");
            paciente.setIdPaciente(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre del paciente:");
            paciente.setNombrePaciente(scanner.nextLine());
            System.out.println("Ingrese el apellido del paciente:");
            paciente.setApellidoPaciente(scanner.nextLine());
            System.out.println("Ingrese la dirección del paciente:");
            paciente.setDireccionPaciente(scanner.nextLine());
            System.out.println("Ingrese el teléfono del paciente:");
            paciente.setTelefonoPaciente(scanner.nextLine());
            System.out.println("Ingrese el email del paciente:");
            paciente.setEmailPaciente(scanner.nextLine());

            // Solicitar el ID de EPS y asignarlo al paciente
            System.out.println("Ingrese el ID de EPS del paciente (No. formato 2xx):");
            int epsId = scanner.nextInt();
            Eps eps = new Eps();
            eps.setIdEps(epsId);
            paciente.setEps(eps);

            String requestBody = mapper.writeValueAsString(paciente);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/pacientes"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Paciente nuevoPaciente = mapper.readValue(response.body(), Paciente.class);
                System.out.println("Paciente guardado exitosamente:");
                System.out.println(nuevoPaciente);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar el paciente. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarPaciente() {
        try {
            System.out.println("Ingrese el ID del paciente a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/pacientes/" + id))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                System.out.println("Paciente eliminado exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("Paciente no encontrado.");
            } else {
                System.out.println("Error al eliminar el paciente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


