package codelicht.sipressspringapp.cliente;

import codelicht.sipressspringapp.modelo.Factura;
import codelicht.sipressspringapp.modelo.Paciente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class FacturaApp {

    private static final String BASE_URL = "http://localhost:8080/sipress-app";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar todas las facturas");
            System.out.println("2. Buscar factura por su número");
            System.out.println("3. Guardar una nueva factura");
            System.out.println("4. Eliminar una factura");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    listarFacturas();
                    break;
                case 2:
                    buscarFacturaPorId();
                    break;
                case 3:
                    guardarFactura();
                    break;
                case 4:
                    eliminarFactura();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nueva.");
            }
        }
    }

    private static void listarFacturas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/facturas"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Factura> facturas = mapper.readValue(response.body(), new TypeReference<>() {
            });
            facturas.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarFacturaPorId() {
        try {
            System.out.println("Ingrese el número de la factura (No. formato 50xxx):");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/facturas/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Factura factura = mapper.readValue(response.body(), Factura.class);
            System.out.println(factura);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarFactura() {
        try {
            Factura factura = new Factura();

            System.out.println("Ingrese el número de la factura (No. formato 50xxx):");
            factura.setNumeroFactura(scanner.nextInt());
            scanner.nextLine();  // Limpiar el buffer del scanner

            // Solicitar el ID del paciente y asignarlo a la factura
            System.out.println("Ingrese el ID del paciente asociado a la factura:");
            int pacienteId = scanner.nextInt();
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(pacienteId);
            factura.setPaciente(paciente);
            scanner.nextLine();  // Limpiar el buffer del scanner

            System.out.println("Ingrese la descripción del servicio facturado:");
            factura.setDescripcionServicio(scanner.nextLine());

            System.out.println("Ingrese el valor del servicio facturado:");
            factura.setValor(scanner.nextDouble());

            System.out.println("Ingrese el valor total a pagar:");
            factura.setTotal(scanner.nextDouble());

            String requestBody = mapper.writeValueAsString(factura);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/facturas"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir el código de estado de la respuesta
            System.out.println("Código de estado de la respuesta: " + response.statusCode());

            if (response.statusCode() == 201 || response.statusCode() == 200) {  // Manejar 201 y 200 como respuestas exitosas
                Factura nuevaFactura = mapper.readValue(response.body(), Factura.class);
                System.out.println("Factura guardada: " + nuevaFactura);
            } else {
                System.out.println("Error al guardar la factura: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void eliminarFactura() {
        try {
            System.out.println("Ingrese el número de la factura a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/facturas/" + id))
                    .DELETE()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() == 204) {
                System.out.println("Factura eliminada.");
            } else {
                System.out.println("Error al eliminar el factura. Código de respuesta: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
