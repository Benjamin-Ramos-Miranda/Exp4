/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp2_s6_benjamin_ramos;

/**
 *
 * @author Benjamin
 */
// Sistema completo: Teatro Moro
import java.util.Scanner;

public class Exp2_S6_Benjamin_Ramos {
    static final String NOMBRE_TEATRO = "Teatro Moro";
    static final int CAPACIDAD_SALA = 30;
    static final double PRECIO_BASE = 5000.0;

    static Entrada[] entradas = new Entrada[CAPACIDAD_SALA];
    static int totalEntradasVendidas = 0;
    static double ingresosTotales = 0.0;
    static int totalReservas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ TEATRO MORO ---");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar una venta existente");
            System.out.println("4. Imprimir boleta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    reservarEntradas(scanner);
                    break;
                case 2:
                    comprarEntradas(scanner);
                    break;
                case 3:
                    modificarVenta(scanner);
                    break;
                case 4:
                    imprimirBoleta(scanner);
                    break;
                case 0:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void reservarEntradas(Scanner sc) {
        mostrarAsientosDisponibles();
        System.out.print("Ingrese número de asiento a reservar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.println("Intentando reservar asiento número: " + numero);

        if (esAsientoValido(numero)) {
            if (entradas[numero] == null) {
                entradas[numero] = new Entrada(numero, "Fila A", PRECIO_BASE, true);
                totalReservas++;
                System.out.println("Asiento reservado correctamente.");
            } else {
                System.out.println("El asiento ya está reservado o comprado.");
            }
        } else {
            System.out.println("Número de asiento inválido.");
        }
    }

    public static void comprarEntradas(Scanner sc) {
        System.out.print("Ingrese número de asiento a comprar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.println("Iniciando proceso de compra para asiento: " + numero);

        Entrada entrada = obtenerEntrada(numero);
        if (entrada != null && entrada.estaReservada()) {
            System.out.print("Tipo de entrada (Normal/Estudiante/Adulto Mayor): ");
            String tipo = sc.nextLine();
            double descuento = aplicarDescuento(tipo);

            double precioFinal = PRECIO_BASE * (1 - descuento);
            entrada.setReservada(false);
            entrada.setPrecioFinal(precioFinal);

            totalEntradasVendidas++;
            ingresosTotales += precioFinal;

            System.out.println("Compra confirmada. Precio final: " + precioFinal);
            System.out.println("Compra realizada con éxito.");
        } else {
            System.out.println("El asiento no está reservado o no existe.");
        }
    }

    public static void modificarVenta(Scanner sc) {
        System.out.print("Ingrese número de asiento para modificar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Entrada entrada = obtenerEntrada(numero);
        if (entrada != null && !entrada.estaReservada()) {
            System.out.print("Nuevo tipo de entrada: ");
            String nuevoTipo = sc.nextLine();
            double nuevoDescuento = aplicarDescuento(nuevoTipo);

            ingresosTotales -= entrada.getPrecioFinal();
            double nuevoPrecio = PRECIO_BASE * (1 - nuevoDescuento);
            entrada.setPrecioFinal(nuevoPrecio);
            ingresosTotales += nuevoPrecio;

            System.out.println("Venta modificada con éxito.");
        } else {
            System.out.println("No se puede modificar esta entrada.");
        }
    }

    public static void imprimirBoleta(Scanner sc) {
        System.out.print("Ingrese número de asiento para imprimir boleta: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.println("Solicitando boleta para asiento: " + numero);

        Entrada entrada = obtenerEntrada(numero);
        if (entrada != null && !entrada.estaReservada()) {
            System.out.println("Datos de boleta - Asiento: " + entrada.getUbicacion() + ", Precio: " + entrada.getPrecioFinal());
            System.out.println("--- BOLETA ---");
            System.out.println("Teatro: " + NOMBRE_TEATRO);
            System.out.println("Asiento: " + entrada.getUbicacion());
            System.out.println("Precio final: $" + entrada.getPrecioFinal());
        } else {
            System.out.println("No hay compra registrada para este asiento.");
        }
    }

    public static void mostrarAsientosDisponibles() {
        System.out.println("--- Asientos Disponibles ---");
        for (int i = 0; i < entradas.length; i++) {
            String estado = (entradas[i] == null) ? "LIBRE" : (entradas[i].estaReservada() ? "RESERVADO" : "COMPRADO");
            System.out.println("Asiento " + i + ": " + estado);
        }
    }

    public static boolean esAsientoValido(int numero) {
        System.out.println("Validando número de asiento: " + numero);
        return numero >= 0 && numero < entradas.length;
    }

    public static Entrada obtenerEntrada(int numero) {
        if (esAsientoValido(numero)) {
            return entradas[numero];
        }
        return null;
    }

    public static double aplicarDescuento(String tipo) {
        switch (tipo.toLowerCase()) {
            case "estudiante":
                return 0.20;
            case "adulto mayor":
                return 0.30;
            default:
                return 0.0;
        }
    }
}

class Entrada {
    private int numero;
    private String ubicacion;
    private double precioFinal;
    private boolean estaReservada;

    public Entrada(int numero, String ubicacion, double precioFinal, boolean estaReservada) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.precioFinal = precioFinal;
        this.estaReservada = estaReservada;
    }

    public String getUbicacion() {
        return ubicacion + ", Asiento " + numero;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public boolean estaReservada() {
        return estaReservada;
    }

    public void setReservada(boolean reservada) {
        this.estaReservada = reservada;
    }
}



