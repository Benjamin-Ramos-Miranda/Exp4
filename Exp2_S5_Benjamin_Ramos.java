/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromoros5;


import java.util.ArrayList;
import java.util.Scanner;

public class Exp2_S5_Benjamin_Ramos {

    // Variables estaticas (globales)
    static String nombreTeatro = "Teatro Moro";
    static int capacidadSala = 100;
    static int entradasDisponibles = capacidadSala;
    static double totalIngresos = 0;
    static int totalEntradasVendidas = 0;

    // Lista de entradas vendidas
    static ArrayList<Entrada> entradasVendidas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENU " + nombreTeatro + " =====");
            System.out.println("1. Venta de entrada");
            System.out.println("2. Ver promociones");
            System.out.println("3. Buscar entrada");
            System.out.println("4. Eliminar entrada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Variables locales
                    String tipoEntrada;
                    String tipoCliente;
                    double precioBase = 0;
                    double descuento = 0;
                    double precioFinalTotal;
                    int cantidad;

                    System.out.print("Ingrese tipo de entrada (VIP, Platea, General): ");
                    tipoEntrada = scanner.nextLine();

                    switch (tipoEntrada.toLowerCase()) {
                        case "vip":
                            precioBase = 20000;
                            break;
                        case "platea":
                            precioBase = 15000;
                            break;
                        case "general":
                            precioBase = 10000;
                            break;
                        default:
                            System.out.println("Tipo de entrada no valido.");
                            continue;
                    }

                    System.out.print("Ingrese tipo de cliente (estudiante, tercera edad, normal): ");
                    tipoCliente = scanner.nextLine();

                    if (tipoCliente.equalsIgnoreCase("estudiante")) {
                        descuento = 0.10;
                    } else if (tipoCliente.equalsIgnoreCase("tercera edad")) {
                        descuento = 0.15;
                    }

                    System.out.print("¿Cuantas entradas desea comprar?: ");
                    cantidad = scanner.nextInt();
                    scanner.nextLine();

                    if (cantidad > entradasDisponibles) {
                        System.out.println("No hay suficientes entradas disponibles.");
                        continue;
                    }

                    if (cantidad >= 3) {
                        System.out.println("Aplica promocion por multiples entradas: 20% adicional.");
                        descuento += 0.20;
                    }

                    precioFinalTotal = (precioBase - (precioBase * descuento)) * cantidad;
                    System.out.println("Precio final: $" + precioFinalTotal);

                    Entrada nuevaEntrada = new Entrada(tipoEntrada, tipoCliente, cantidad, precioFinalTotal);
                    entradasVendidas.add(nuevaEntrada);

                    entradasDisponibles -= cantidad;
                    totalEntradasVendidas += cantidad;
                    totalIngresos += precioFinalTotal;

                    System.out.println("Entrada vendida con exito.");
                    break;

                case 2:
                    System.out.println("--- Promociones Disponibles ---");
                    System.out.println("1. 10% de descuento para estudiantes.");
                    System.out.println("2. 15% de descuento para personas de la tercera edad.");
                    System.out.println("3. 20% de descuento adicional al comprar 3 o mas entradas.");
                    break;

                case 3:
                    System.out.println("--- Busqueda de Entradas ---");
                    System.out.println("1. Buscar por numero");
                    System.out.println("2. Buscar por ubicacion");
                    System.out.println("3. Buscar por tipo de cliente");
                    System.out.print("Seleccione una opcion: ");
                    int opcionBusqueda = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionBusqueda) {
                        case 1:
                            System.out.print("Ingrese numero de entrada (posicion): ");
                            int indice = scanner.nextInt();
                            scanner.nextLine();
                            if (indice >= 0 && indice < entradasVendidas.size()) {
                                System.out.println(entradasVendidas.get(indice));
                            } else {
                                System.out.println("Entrada no encontrada.");
                            }
                            break;

                        case 2:
                            System.out.print("Ingrese tipo de ubicacion (VIP, Platea, General): ");
                            String ubicacion = scanner.nextLine();
                            boolean encontrada = false;
                            for (Entrada e : entradasVendidas) {
                                if (e.tipo.equalsIgnoreCase(ubicacion)) {
                                    System.out.println(e);
                                    encontrada = true;
                                }
                            }
                            if (!encontrada) {
                                System.out.println("No se encontraron entradas con esa ubicacion.");
                            }
                            break;

                        case 3:
                            System.out.print("Ingrese tipo de cliente (estudiante, tercera edad, normal): ");
                            String clienteBuscado = scanner.nextLine();
                            boolean hallada = false;
                            for (Entrada e : entradasVendidas) {
                                if (e.cliente.equalsIgnoreCase(clienteBuscado)) {
                                    System.out.println(e);
                                    hallada = true;
                                }
                            }
                            if (!hallada) {
                                System.out.println("No se encontraron entradas para ese tipo de cliente.");
                            }
                            break;

                        default:
                            System.out.println("Opcion invalida.");
                    }
                    break;

                case 4:
                    System.out.println("--- Eliminar Entrada ---");
                    if (entradasVendidas.isEmpty()) {
                        System.out.println("No hay entradas para eliminar.");
                        break;
                    }

                    System.out.println("Entradas disponibles para eliminar:");
                    for (int i = 0; i < entradasVendidas.size(); i++) {
                        System.out.println("[" + i + "] " + entradasVendidas.get(i));
                    }

                    System.out.print("Ingrese numero de entrada a eliminar (posicion): ");
                    int numEliminar = scanner.nextInt();
                    scanner.nextLine();

                    if (numEliminar >= 0 && numEliminar < entradasVendidas.size()) {
                        Entrada eliminada = entradasVendidas.remove(numEliminar);
                        entradasDisponibles += eliminada.cantidad;
                        totalEntradasVendidas -= eliminada.cantidad;
                        totalIngresos -= eliminada.precioTotal;

                        System.out.println("Entrada eliminada: " + eliminada);
                    } else {
                        System.out.println("Numero de entrada invalido.");
                    }
                    break;

                case 5:
                    salir = true;
                    System.out.println("Gracias por usar el sistema del " + nombreTeatro + "!");
                    System.out.println("Entradas vendidas: " + totalEntradasVendidas);
                    System.out.println("Total ingresos: $" + totalIngresos);
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }

        scanner.close();
    }
}

// Clase Entrada con variables de instancia
class Entrada {
    String tipo;
    String cliente;
    int cantidad;
    double precioTotal;

    public Entrada(String tipo, String cliente, int cantidad, double precioTotal) {
        this.tipo = tipo;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public String toString() {
        return "Tipo: " + tipo + ", Cliente: " + cliente + ", Cantidad: " + cantidad + ", Precio Total: $" + precioTotal;
    }
}




