package codelicht.sipressspringapp.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClientApp {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Guardar un nuevo usuario");
            System.out.println("4. Eliminar un usuario");
            System.out.println("5. Listar todos los pacientes");
            System.out.println("6. Buscar paciente por ID");
            System.out.println("7. Guardar un nuevo paciente");
            System.out.println("8. Eliminar un paciente");
            System.out.println("9. Listar todos los empleados");
            System.out.println("10. Buscar empleado por ID");
            System.out.println("11. Guardar un nuevo empleado");
            System.out.println("12. Eliminar un empleado");
            System.out.println("13. Listar todos los historiales");
            System.out.println("14. Buscar historial por ID");
            System.out.println("15. Guardar un nuevo historial");
            System.out.println("16. Eliminar un historial");
            System.out.println("17. Salir");

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
                    listarPacientes();
                    break;
                case 6:
                    buscarPacientePorId();
                    break;
                case 7:
                    guardarPaciente();
                    break;
                case 8:
                    eliminarPaciente();
                    break;
                case 9:
                    listarEmpleados();
                    break;
                case 10:
                    buscarEmpleadoPorId();
                    break;
                case 11:
                    guardarEmpleado();
                    break;
                case 12:
                    eliminarEmpleado();
                    break;
                case 13:
                    listarHistoriales();
                    break;
                case 14:
                    buscarHistorialPorId();
                    break;
                case 15:
                    guardarHistorial();
                    break;
                case 16:
                    eliminarHistorial();
                    break;
                case 17:
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
        System.out.println("Listar usuarios: " + response.body());
    }

    private static void buscarUsuarioPorId() throws Exception {
        System.out.print("Ingrese el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar usuario por ID: " + response.body());
    }

    private static void guardarUsuario() throws Exception {
        System.out.print("Ingrese el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();

        String json = String.format("{\"id\": %d, \"nombre\": \"%s\"}", id, nombre);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/usuarios"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar usuario: " + response.body());
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
        System.out.println("Eliminar usuario: " + response.body());
    }

    // Métodos para pacientes
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
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar paciente por ID: " + response.body());
    }

    private static void guardarPaciente() throws Exception {
        System.out.print("Ingrese el ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea
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
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/pacientes/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar paciente: " + response.body());
    }

    // Métodos para empleados
    private static void listarEmpleados() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Listar empleados: " + response.body());
    }

    private static void buscarEmpleadoPorId() throws Exception {
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar empleado por ID: " + response.body());
    }

    private static void guardarEmpleado() throws Exception {
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();

        String json = String.format("{\"id\": %d, \"nombre\": \"%s\"}", id, nombre);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/empleados"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar empleado: " + response.body());
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
        System.out.println("Eliminar empleado: " + response.body());
    }

    // Métodos para historiales
    private static void listarHistoriales() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/historiales"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Listar historiales: " + response.body());
    }

    private static void buscarHistorialPorId() throws Exception {
        System.out.print("Ingrese el ID del historial: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/historiales/" + id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Buscar historial por ID: " + response.body());
    }

    private static void guardarHistorial() throws Exception {
        System.out.print("Ingrese el ID del historial: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea
        System.out.print("Ingrese la descripción del historial: ");
        String descripcion = scanner.nextLine();

        String json = String.format("{\"id\": %d, \"descripcion\": \"%s\"}", id, descripcion);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/historiales"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Guardar historial: " + response.body());
    }

    private static void eliminarHistorial() throws Exception {
        System.out.print("Ingrese el ID del historial a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Integra una nueva línea

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sipress-spring-app/historiales/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Eliminar historial: " + response.body());
    }
}

