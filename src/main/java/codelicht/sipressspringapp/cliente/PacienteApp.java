package codelicht.sipressspringapp.cliente;

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
            List<Paciente> pacientes = mapper.readValue(response.body(), new TypeReference<List<Paciente>>() {
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
            Scanner scanner = new Scanner(System.in);
            ObjectMapper mapper = new ObjectMapper();

            Paciente paciente = new Paciente();
            System.out.println("Ingrese el ID del paciente:");
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

            String requestBody = mapper.writeValueAsString(paciente);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sipress-app/pacientes"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {  // Código 201 indica creación exitosa
                Paciente nuevoPaciente = mapper.readValue(response.body(), Paciente.class);
                System.out.println("Paciente guardado: " + nuevoPaciente);
            } else {
                System.out.println("Error al guardar el paciente: " + response.body());
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
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Paciente eliminado.");
            } else {
                System.out.println("Error al eliminar el paciente. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


