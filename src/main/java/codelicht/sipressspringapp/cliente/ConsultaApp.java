package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Consulta;
import codelicht.sipressspringapp.modelo.ConsultaPK;
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

public class ConsultaApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app/consultas";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las consultas");
            System.out.println("2. Buscar consulta por fecha");
            System.out.println("3. Buscar consulta por hora");
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
                    buscarConsultasPorFecha();
                    break;
                case 3:
                    buscarConsultasPorHora();
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
                    .uri(URI.create(BASE_URL))
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

    private static void buscarConsultasPorFecha() {
        try {
            System.out.println("Ingrese la fecha de la consulta (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            Date fechaConsulta = dateFormat.parse(fechaInput);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/fecha/" + dateFormat.format(fechaConsulta)))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consulta> consultas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultas.forEach(System.out::println);
        } catch (ParseException e) {
            System.err.println("Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarConsultasPorHora() {
        try {
            System.out.println("Ingrese la hora de la consulta (formato: HH:mm):");
            String horaInput = scanner.nextLine();
            Date horaConsulta = timeFormat.parse(horaInput);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/hora/" + timeFormat.format(horaConsulta)))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Consulta> consultas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            consultas.forEach(System.out::println);
        } catch (ParseException e) {
            System.err.println("Formato de hora incorrecto. Use el formato HH:mm.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarConsulta() {
        try {
            Consulta consulta = new Consulta();
            ConsultaPK consultaPK = new ConsultaPK();

            System.out.println("Ingrese el ID del doctor:");
            consultaPK.setDoctorId(scanner.nextInt());
            System.out.println("Ingrese el ID del paciente:");
            consultaPK.setPacienteId(scanner.nextInt());
            scanner.nextLine(); // Limpiar el buffer del scanner

            consulta.setConsultaPK(consultaPK);

            System.out.println("Ingrese la fecha de la consulta (formato: yyyy-MM-dd):");
            String fechaInput = scanner.nextLine();
            Date fechaConsulta = dateFormat.parse(fechaInput);
            consulta.setFechaConsulta(fechaConsulta);

            System.out.println("Ingrese la hora de la consulta (formato: HH:mm):");
            String horaInput = scanner.nextLine();
            Date horaConsulta = timeFormat.parse(horaInput);
            consulta.setHoraConsulta(horaConsulta);

            // Convertir el objeto consulta a JSON
            String requestBody = mapper.writeValueAsString(consulta);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Consulta nuevaConsulta = mapper.readValue(response.body(), Consulta.class);
            System.out.println("Consulta guardada: " + nuevaConsulta);
        } catch (ParseException e) {
            System.err.println("Formato de fecha/hora incorrecto. Use el formato yyyy-MM-dd para la fecha y HH:mm para la hora.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarConsulta() {
        try {
            System.out.println("Ingrese el ID del doctor:");
            int doctorId = scanner.nextInt();
            System.out.println("Ingrese el ID del paciente:");
            int pacienteId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + pacienteId + "/" + doctorId))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Consulta eliminada.");
            } else {
                System.out.println("Error al eliminar la consulta. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

