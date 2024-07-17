package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Dependencia;
import codelicht.sipressspringapp.modelo.Doctor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class DoctorApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todos los doctores");
            System.out.println("2. Buscar doctor por ID");
            System.out.println("3. Guardar nuevo doctor");
            System.out.println("4. Eliminar un doctor");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarDoctores();
                    break;
                case 2:
                    buscarDoctorPorId();
                    break;
                case 3:
                    guardarDoctor();
                    break;
                case 4:
                    eliminarDoctor();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarDoctores() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/doctores"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Doctor> doctores = mapper.readValue(response.body(), new TypeReference<>() {
            });
            doctores.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarDoctorPorId() {
        try {
            System.out.println("Ingrese el ID del doctor:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/doctores/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Doctor doctor = mapper.readValue(response.body(), Doctor.class);
            System.out.println(doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarDoctor() {
        try {
            Scanner scanner = new Scanner(System.in);
            ObjectMapper mapper = new ObjectMapper();

            Doctor doctor = new Doctor();
            System.out.println("Ingrese el ID del doctor:");
            doctor.setIdDoctor(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner
            System.out.println("Ingrese el nombre del doctor:");
            doctor.setNombreDoctor(scanner.nextLine());
            System.out.println("Ingrese el apellido del doctor:");
            doctor.setApellidoDoctor(scanner.nextLine());

            // Solicitar el ID de la dependencia y asignarlo al doctor
            System.out.println("Ingrese el ID de la dependencia del doctor (No. formato 14xx):");
            int dependenciaId = scanner.nextInt();
            Dependencia dependencia = new Dependencia();
            dependencia.setIdDependencia(dependenciaId);
            doctor.setDependencia(dependencia);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese el teléfono del doctor:");
            doctor.setTelefonoDoctor(scanner.nextLine());
            System.out.println("Ingrese el email del doctor:");
            doctor.setEmailDoctor(scanner.nextLine());

            String requestBody = mapper.writeValueAsString(doctor);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/sipress-app/doctores"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {  // Código 201 indica creación exitosa
                Doctor nuevoDoctor = mapper.readValue(response.body(), Doctor.class);
                System.out.println("Doctor guardado: " + nuevoDoctor);
            } else {
                System.out.println("Error al guardar el doctor: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarDoctor() {
        try {
            System.out.println("Ingrese el ID del doctor a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/doctores/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Doctor eliminado.");
            } else {
                System.out.println("Error al eliminar el doctor. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
