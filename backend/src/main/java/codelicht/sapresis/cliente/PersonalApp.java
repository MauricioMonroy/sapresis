package codelicht.sapresis.cliente;

import codelicht.sapresis.modelo.Dependencia;
import codelicht.sapresis.modelo.Personal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class PersonalApp {

    private static final String BASE_URL = "http://localhost:8080/sapresis";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los miembros del personal");
            System.out.println("2. Buscar personal por ID");
            System.out.println("3. Guardar un nuevo personal");
            System.out.println("4. Eliminar un personal");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarPersonalS();
                    break;
                case 2:
                    buscarPersonalPorId();
                    break;
                case 3:
                    guardarPersonal();
                    break;
                case 4:
                    eliminarPersonal();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarPersonalS() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/personalS"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Personal> personalS = mapper.readValue(response.body(), new TypeReference<>() {
            });
            personalS.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarPersonalPorId() {
        try {
            System.out.println("Ingrese el ID del personal (No. formato 41xx):");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/personalS/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Personal personal = mapper.readValue(response.body(), Personal.class);
            System.out.println(personal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarPersonal() {
        try {
            Personal personal = new Personal();

            System.out.println("Ingrese el ID del personal (No. formato 41xx):");
            personal.setIdPersonal(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID de la dependencia y asignarlo al personal
            System.out.println("Ingrese el ID de la dependencia del personal (No. formato 14xx):");
            int dependenciaId = scanner.nextInt();
            Dependencia dependencia = new Dependencia();
            dependencia.setIdDependencia(dependenciaId);
            personal.setDependencia(dependencia);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el nombre del personal:");
            personal.setNombrePersonal(scanner.nextLine());
            System.out.println("Ingrese el apellido del personal:");
            personal.setApellidoPersonal(scanner.nextLine());
            System.out.println("Ingrese el teléfono del personal:");
            personal.setTelefonoPersonal(scanner.nextLine());
            System.out.println("Ingrese el email del personal:");
            personal.setEmailPersonal(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(personal);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/personalS"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Personal nuevoPersonal = mapper.readValue(response.body(), Personal.class);
                System.out.println("Personal guardado exitosamente:");
                System.out.println(nuevoPersonal);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar el personal. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarPersonal() {
        try {
            System.out.println("Ingrese el ID del personal a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/personalS/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Personal eliminado exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("Personal no encontrado.");
            } else {
                System.out.println("Error al eliminar el personal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



