package codelicht.sipresswebapp.cliente;

import codelicht.sipresswebapp.modelo.Eps;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class EpsApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las EPS");
            System.out.println("2. Buscar eps por ID");
            System.out.println("3. Guardar una nueva EPS");
            System.out.println("4. Eliminar una EPS");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarEpsS();
                    break;
                case 2:
                    buscarEpsPorId();
                    break;
                case 3:
                    guardarEps();
                    break;
                case 4:
                    eliminarEps();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nueva.");
            }
        }
    }

    private static void listarEpsS() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/epsS"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Eps> epsS = mapper.readValue(response.body(), new TypeReference<>() {
            });
            epsS.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarEpsPorId() {
        try {
            System.out.println("Ingrese el ID de la EPS (No. formato 2xx):");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/epsS/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Eps eps = mapper.readValue(response.body(), Eps.class);
            System.out.println(eps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarEps() {
        try {
            Eps eps = new Eps();
            System.out.println("Ingrese el ID de la EPS (No. formato 2xx):");
            eps.setIdEps(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre de la EPS:");
            eps.setNombreEps(scanner.nextLine());

            System.out.println("Ingrese el teléfono de la EPS:");
            eps.setTelefonoEps(scanner.nextLine());

            System.out.println("Ingrese el email de la EPS:");
            eps.setEmailEps(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(eps);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/epsS"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {  // Código 201 indica creación exitosa
                Eps nuevaEps = mapper.readValue(response.body(), Eps.class);
                System.out.println("EPS guardada exitosamente:");
                System.out.println(nuevaEps);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar la EPS. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarEps() {
        try {
            System.out.println("Ingrese el ID de la EPS a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/epsS/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("EPS eliminada exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("EPS no encontrado.");
            } else {
                System.out.println("Error al eliminar la EPS.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

