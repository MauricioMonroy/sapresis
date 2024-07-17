package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Consultorio;
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

            System.out.println("Ingrese la fecha de admisión (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            Date fechaAdmision = dateFormat.parse(fechaInput);
            consultorio.setFechaAdmision(fechaAdmision);

            // Convertir el objeto consultorio a JSON
            String requestBody = mapper.writeValueAsString(consultorio);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Consultorio nuevoConsultorio = mapper.readValue(response.body(), Consultorio.class);
            System.out.println("Consultorio guardado: " + nuevoConsultorio);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
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
                System.out.println("Consultorio eliminado.");
            } else {
                System.out.println("Error al eliminar la consultorio. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
