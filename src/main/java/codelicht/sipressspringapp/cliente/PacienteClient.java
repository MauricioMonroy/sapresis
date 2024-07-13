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
            scanner.nextLine(); // Integra una nueva línea

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
        System.out.println("Listar pacientes:");
        prettyPrint(response.body());
    }

    private static void buscarPacientePorId() throws Exception {
        System.out.print("Ingrese el ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar paciente por ID:");
        prettyPrint(response.body());
    }

    private static void guardarPaciente() throws Exception {
        System.out.print("Ingrese el detalle de EPS: ");
        String detalleEps = scanner.nextLine();
        System.out.print("Ingrese la fecha de consulta (yyyy-MM-dd): ");
        String fechaConsulta = scanner.nextLine();
        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el email: ");
        String email = scanner.nextLine();
        System.out.print("¿Es paciente? (true/false): ");
        boolean esPaciente = scanner.nextBoolean();
        System.out.print("¿Es empleado? (true/false): ");
        boolean esEmpleado = scanner.nextBoolean();
        scanner.nextLine(); // Integra una nueva línea

        String json = String.format(
                "{\"detalleEps\": \"%s\", \"fechaConsulta\": \"%s\", \"username\": \"%s\", \"password\": \"%s\", \"nombre\": \"%s\", \"apellido\": \"%s\", \"identificacion\": \"%s\", \"telefono\": \"%s\", \"email\": \"%s\", \"esPaciente\": %b, \"esEmpleado\": %b}",
                detalleEps, fechaConsulta, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar paciente:");
        prettyPrint(response.body());
    }

    private static void eliminarPaciente() throws Exception {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar paciente:");
        prettyPrint(response.body());
    }

    private static void prettyPrint(String responseBody) {
        String[] entries = responseBody.split("},\\{");
        for (String entry : entries) {
            System.out.println(entry.replace("{", "").replace("}", "").replace(",", "\n"));
            System.out.println("------------");
        }
    }
}


