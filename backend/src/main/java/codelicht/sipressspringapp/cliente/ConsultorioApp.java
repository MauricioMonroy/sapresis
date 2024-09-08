package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Consultorio;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.modelo.Personal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsultorioApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los consultorios");
            System.out.println("2. Buscar consultorio por su número");
            System.out.println("3. Guardar un nuevo consultorio");
            System.out.println("4. Eliminar un consultorio");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarConsultorios();
                    break;
                case 2:
                    buscarConsultorioPorId();
                    break;
                case 3:
                    guardarConsultorio();
                    break;
                case 4:
                    eliminarConsultorio();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarConsultorios() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultorios"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consultorio> consultorios = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultorios.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarConsultorioPorId() {
        try {
            System.out.println("Ingrese el número del consultorio:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultorios/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Consultorio consultorio = mapper.readValue(response.body(), Consultorio.class);
            System.out.println(consultorio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarConsultorio() {
        try {
            Consultorio consultorio = new Consultorio();

            System.out.println("Ingrese el número del consultorio:");
            consultorio.setNumeroConsultorio(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID del consultorio y asignarlo al consultorio
            System.out.println("Ingrese el ID del consultorio asociado al consultorio:");
            int pacienteId = scanner.nextInt();
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(pacienteId);
            consultorio.setPaciente(paciente);
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID del personal y asignarlo al consultorio
            System.out.println("Ingrese el ID del personal asociado al consultorio (No. Formato 41xx):");
            int personalId = scanner.nextInt();
            Personal personal = new Personal();
            personal.setIdPersonal(personalId);
            consultorio.setPersonal(personal);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese la fecha de admisión (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            LocalDate fechaAdmision = LocalDate.parse(fechaInput);
            consultorio.setFechaAdmision(fechaAdmision);

            String requestBody = mapper.writeValueAsString(consultorio);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultorios"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Consultorio nuevoConsultorio = mapper.readValue(response.body(), Consultorio.class);
                System.out.println("Consultorio guardado exitosamente:");
                System.out.println(nuevoConsultorio);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar el consultorio. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarConsultorio() {
        try {
            System.out.println("Ingrese el número del consultorio a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultorios/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Consultorio eliminado exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("Consultorio no encontrado.");
            } else {
                System.out.println("Error al eliminar el consultorio.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
