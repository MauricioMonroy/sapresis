package codelicht.sapresis.cliente;

import codelicht.sapresis.modelo.Consulta;
import codelicht.sapresis.modelo.Doctor;
import codelicht.sapresis.modelo.Paciente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsultaApp {

    private static final String BASE_URL = "http://localhost:8080/sapresis";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las consultas");
            System.out.println("2. Buscar consultas por ID de paciente");
            System.out.println("3. Buscar consultas por ID de doctor");
            System.out.println("4. Guardar una nueva consulta");
            System.out.println("5. Eliminar una consulta");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarConsultas();
                    break;
                case 2:
                    buscarConsultasPorIdConsulta();
                    break;
                case 3:
                    buscarConsultasPorIdDoctor();
                    break;
                case 4:
                    guardarConsulta();
                    break;
                case 5:
                    eliminarConsulta();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void listarConsultas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultas"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consulta> consultas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultas.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarConsultasPorIdConsulta() {
        try {
            System.out.println("Ingrese el ID del paciente:");
            int pacienteId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultas/paciente/" + pacienteId))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consulta> consultas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultas.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarConsultasPorIdDoctor() {
        try {
            System.out.println("Ingrese el ID del doctor:");
            int doctorId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultas/doctor/" + doctorId))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consulta> consultas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultas.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarConsulta() {
        try {
            Consulta consulta = new Consulta();

            // Solicitar el ID del paciente y asignarlo a la consulta
            System.out.println("Ingrese el ID del paciente asociado a la consulta:");
            int pacienteId = scanner.nextInt();
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(pacienteId);
            consulta.setPaciente(paciente);
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID del doctor y asignarlo a la consulta
            System.out.println("Ingrese el ID del doctor asociado a la consulta (No. Formato 2xxx):");
            int doctorId = scanner.nextInt();
            Doctor doctor = new Doctor();
            doctor.setIdDoctor(doctorId);
            consulta.setDoctor(doctor);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese la fecha de consulta (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            LocalDate fechaConsulta = LocalDate.parse(fechaInput);
            consulta.setFechaConsulta(fechaConsulta);

            System.out.println("Ingrese la hora de consulta (formato: HH:mm:ss):");
            String horaInput = scanner.nextLine();
            Date horaConsulta = timeFormat.parse(horaInput);
            consulta.setHoraConsulta(horaConsulta);

            String requestBody = mapper.writeValueAsString(consulta);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultas"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Consulta nuevaConsulta = mapper.readValue(response.body(), Consulta.class);
                System.out.println("Consulta guardada exitosamente:");
                System.out.println(nuevaConsulta);
            } else if (response.statusCode() == 400) {
                List<String> errors = mapper.readValue(response.body(), new TypeReference<>() {
                });
                System.out.println("Errores de validación:");
                errors.forEach(System.out::println);
            } else {
                System.out.println("Error al guardar la consulta. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarConsulta() {
        try {
            System.out.println("Ingrese el ID del paciente:");
            int pacienteId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.println("Ingrese el ID del doctor:");
            int doctorId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/consultas/" + pacienteId + "/" + doctorId))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                System.out.println("Consulta eliminada exitosamente.");
            } else if (response.statusCode() == 404) {
                System.out.println("Consulta no encontrada.");
            } else {
                System.out.println("Error al eliminar la consulta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


