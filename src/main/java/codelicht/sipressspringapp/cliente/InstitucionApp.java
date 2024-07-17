package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Institucion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class InstitucionApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos las instituciones");
            System.out.println("2. Buscar institucion por ID");
            System.out.println("3. Guardar nueva institucion");
            System.out.println("4. Eliminar una institucion");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarInstituciones();
                    break;
                case 2:
                    buscarInstitucionPorId();
                    break;
                case 3:
                    guardarInstitucion();
                    break;
                case 4:
                    eliminarInstitucion();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nueva.");
            }
        }
    }

    private static void listarInstituciones() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/instituciones"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Institucion> instituciones = mapper.readValue(response.body(), new TypeReference<>() {
            });
            instituciones.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarInstitucionPorId() {
        try {
            System.out.println("Ingrese el ID de la institución:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/instituciones/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Institucion institucion = mapper.readValue(response.body(), Institucion.class);
            System.out.println(institucion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarInstitucion() {
        try {
            Scanner scanner = new Scanner(System.in);
            ObjectMapper mapper = new ObjectMapper();

            Institucion institucion = new Institucion();
            System.out.println("Ingrese el ID de la institución (No. formato 1xx):");
            institucion.setIdInstitucion(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner
            System.out.println("Ingrese el nombre de la institución:");
            institucion.setNombreInstitucion(scanner.nextLine());
            System.out.println("Ingrese la dirección de la institución:");
            institucion.setDireccionInstitucion(scanner.nextLine());
            System.out.println("Ingrese el teléfono de la institución:");
            institucion.setTelefonoInstitucion(scanner.nextLine());
            System.out.println("Ingrese el email del institucion:");
            institucion.setCodigoPostal(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(institucion);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sipress-app/instituciones"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {  // Código 201 indica creación exitosa
                Institucion nuevaInstitucion = mapper.readValue(response.body(), Institucion.class);
                System.out.println("Institucion guardada: " + nuevaInstitucion);
            } else {
                System.out.println("Error al guardar la institución: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarInstitucion() {
        try {
            System.out.println("Ingrese el ID de la institución a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/instituciones/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Institucion eliminada.");
            } else {
                System.out.println("Error al eliminar la institución. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
