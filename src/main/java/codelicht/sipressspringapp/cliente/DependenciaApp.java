package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Dependencia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class DependenciaApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las dependencias");
            System.out.println("2. Buscar dependencia por ID");
            System.out.println("3. Guardar una nueva dependencia");
            System.out.println("4. Eliminar una dependencia");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarDependencias();
                    break;
                case 2:
                    buscarDependenciaPorId();
                    break;
                case 3:
                    guardarDependencia();
                    break;
                case 4:
                    eliminarDependencia();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nueva.");
            }
        }
    }

    private static void listarDependencias() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/dependencias"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Dependencia> dependencias = mapper.readValue(response.body(), new TypeReference<>() {
            });
            dependencias.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarDependenciaPorId() {
        try {
            System.out.println("Ingrese el ID de la dependencia:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/dependencias/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Dependencia dependencia = mapper.readValue(response.body(), Dependencia.class);
            System.out.println(dependencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarDependencia() {
        try {
            Dependencia dependencia = new Dependencia();
            System.out.println("Ingrese el ID de la dependencia:");
            dependencia.setIdDependencia(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre de la dependencia:");
            dependencia.setNombreDependencia(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(dependencia);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sipress-app/dependencias"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {  // Código 201 indica creación exitosa
                Dependencia nuevaDependencia = mapper.readValue(response.body(), Dependencia.class);
                System.out.println("Dependencia guardada: " + nuevaDependencia);
            } else {
                System.out.println("Error al guardar la dependencia: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarDependencia() {
        try {
            System.out.println("Ingrese el ID de la dependencia a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/dependencias/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Dependencia eliminada.");
            } else {
                System.out.println("Error al eliminar el dependencia. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
