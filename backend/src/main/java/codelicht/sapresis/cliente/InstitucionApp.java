package codelicht.sapresis.cliente;

import codelicht.sapresis.modelo.Institucion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class InstitucionApp {

    private static final String BASE_URL = "http://localhost:8080/sapresis";
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
            System.out.println("Ingrese el ID de la institución (No. formato 1xx):");
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
            Institucion institucion = new Institucion();

            System.out.println("Ingrese el ID dla institucion (No. formato 1xx):");
            institucion.setIdInstitucion(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre de la institución:");
            institucion.setNombreInstitucion(scanner.nextLine());
            System.out.println("Ingrese la dirección de la institución:");
            institucion.setDireccionInstitucion(scanner.nextLine());
            System.out.println("Ingrese el teléfono de la institución:");
            institucion.setTelefonoInstitucion(scanner.nextLine());
            System.out.println("Ingrese el email dla institucion:");
            institucion.setCodigoPostal(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(institucion);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/instituciones"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Institucion nuevaInstitucion = mapper.readValue(response.body(), Institucion.class);
                System.out.println("Institucion guardada exitosamente:");
                System.out.println(nuevaInstitucion);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar la institucion. Código de estado: " + response.statusCode());
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
                System.out.println("Institucion eliminada exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("Institucion no encontrado.");
            } else {
                System.out.println("Error al eliminar la institucion.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
