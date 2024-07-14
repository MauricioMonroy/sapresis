package codelicht.sipressspringapp.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class UsuarioClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Guardar un nuevo usuario");
            System.out.println("4. Eliminar un usuario");
            System.out.println("5. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Integra una nueva línea

            switch (choice) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    buscarUsuarioPorId();
                    break;
                case 3:
                    guardarUsuario();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    private static void listarUsuarios() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Listar usuarios:");
        prettyPrint(response.body());
    }

    private static void buscarUsuarioPorId() throws Exception {
        System.out.print("Ingrese el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar usuario por ID:");
        prettyPrint(response.body());
    }

    private static void guardarUsuario() throws Exception {
        System.out.print("Ingrese el username: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del usuario: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la identificación del usuario: ");
        String identificacion = scanner.nextLine();
        System.out.print("Ingrese el teléfono del usuario: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        System.out.print("¿Es paciente? (true/false): ");
        boolean esPaciente = scanner.nextBoolean();
        System.out.print("¿Es empleado? (true/false): ");
        boolean esEmpleado = scanner.nextBoolean();
        scanner.nextLine();

        String json = String.format(
                "{\"username\": \"%s\", \"nombre\": \"%s\", \"apellido\": \"%s\", \"identificacion\": \"%s\", \"telefono\": \"%s\", \"email\": \"%s\", \"password\": \"%s\", \"esPaciente\": %b, \"esEmpleado\": %b}",
                username, nombre, apellido, identificacion, telefono, email, password, esPaciente, esEmpleado
        );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar usuario:");
        prettyPrint(response.body());
    }

    private static void eliminarUsuario() throws Exception {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar usuario:");
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
