package codelicht.sipressspringapp.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class EmpleadoClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los empleados");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Guardar un nuevo empleado");
            System.out.println("4. Eliminar un empleado");
            System.out.println("5. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Integra una nueva línea

            switch (choice) {
                case 1:
                    listarEmpleados();
                    break;
                case 2:
                    buscarEmpleadoPorId();
                    break;
                case 3:
                    guardarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    private static void listarEmpleados() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Listar empleados:");
        prettyPrint(response.body());
    }

    private static void buscarEmpleadoPorId() throws Exception {
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar empleado por ID:");
        prettyPrint(response.body());
    }

    private static void guardarEmpleado() throws Exception {
        System.out.print("Ingrese el detalle de EPS: ");
        String cargo = scanner.nextLine();
        System.out.print("Ingrese la fecha de consulta (yyyy-MM-dd): ");
        Double sueldo = scanner.nextDouble();
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
        System.out.print("¿Es empleado? (true/false): ");
        boolean esPaciente = scanner.nextBoolean();
        System.out.print("¿Es empleado? (true/false): ");
        boolean esEmpleado = scanner.nextBoolean();
        scanner.nextLine(); // Integra una nueva línea

        String json = String.format(
                "{\"cargo\": \"%s\", \"sueldo\": \"%s\", \"username\": \"%s\", \"password\": \"%s\", \"nombre\": \"%s\", \"apellido\": \"%s\", \"identificacion\": \"%s\", \"telefono\": \"%s\", \"email\": \"%s\", \"esEmpleado\": %b, \"esEmpleado\": %b}",
                cargo, sueldo, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar empleado:");
        prettyPrint(response.body());
    }

    private static void eliminarEmpleado() throws Exception {
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar empleado:");
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
