package codelicht.sipressspringapp.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PacienteClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los pacientes");
            System.out.println("2. Buscar paciente por ID");
            System.out.println("3. Guardar un nuevo paciente");
            System.out.println("4. Eliminar un paciente");
            System.out.println("5. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
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
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    private static void listarPacientes() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Listar pacientes: " + response.body());
    }

    private static void buscarPacientePorId() throws Exception {
        System.out.print("Ingrese el ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar paciente por ID: " + response.body());
    }

    private static void guardarPaciente() throws Exception {
        System.out.print("Ingrese el ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Ingrese el detalle de EPS: ");
        String detalleEps = scanner.nextLine();
        System.out.print("Ingrese la fecha de consulta (yyyy-MM-dd): ");
        String fechaConsulta = scanner.nextLine();

        String json = String.format("{\"id\": %d, \"detalleEps\": \"%s\", \"fechaConsulta\": \"%s\"}", id, detalleEps, fechaConsulta);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar paciente: " + response.body());
    }

    private static void eliminarPaciente() throws Exception {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar paciente: " + response.body());
    }
}

