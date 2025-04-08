/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromoros4;

/**
 *
 * @author Benjamin
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exp2_S4_Benjamin_Ramos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        for (;;) {
            System.out.println("========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();

                if (opcion == 1) {
                    //  Inicialización variable
                    String continuar = "s";

                    do {
                        System.out.println("\n========== PLANO DEL TEATRO ==========");
                        System.out.println("Zona A - $10000");
                        System.out.println("Zona B - $7000");
                        System.out.println("Zona C - $5000");
                        System.out.print("Ingrese la zona que desea (A, B o C): ");
                        char zona = scanner.next().toUpperCase().charAt(0);

                        int precioBase = 0;
                        String zonaTexto = "";

                        if (zona == 'A') {
                            precioBase = 10000;
                            zonaTexto = "Zona A";
                        } else if (zona == 'B') {
                            precioBase = 7000;
                            zonaTexto = "Zona B";
                        } else if (zona == 'C') {
                            precioBase = 5000;
                            zonaTexto = "Zona C";
                        } else {
                            System.out.println("Zona inválida. Intente nuevamente.\n");
                            continue;
                        }

                        System.out.print("Ingrese su edad: ");
                        int edad = 0;

                        try {
                            edad = scanner.nextInt();
                            if (edad <= 0 || edad > 120) {
                                System.out.println("Edad no válida. Intente nuevamente.\n");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Edad inválida. Debe ingresar un número.\n");
                            scanner.next(); 
                            continue;
                        }

                        double descuento = 0;
                        String tipoDescuento = "Ninguno";

                        if (edad < 25) {
                            descuento = 0.10;
                            tipoDescuento = "Estudiante (10%)";
                        } else if (edad >= 60) {
                            descuento = 0.15;
                            tipoDescuento = "Tercera Edad (15%)";
                        }

                        double precioFinal = precioBase;
                        int i = 0;
                        while (i < 1) {
                            precioFinal = precioBase - (precioBase * descuento);
                            i++;
                        }

                        System.out.println("\n========== RESUMEN DE LA COMPRA ==========");
                        System.out.println("Ubicación del asiento: " + zonaTexto);
                        System.out.println("Precio base: $" + precioBase);
                        System.out.println("Descuento aplicado: " + tipoDescuento);
                        System.out.println("Precio final a pagar: $" + precioFinal);
                        System.out.println("===========================================\n");

                        System.out.print("¿Desea realizar otra compra? (s/n): ");
                        continuar = scanner.next();

                    } while (continuar.equalsIgnoreCase("s"));

                } else if (opcion == 2) {
                    System.out.println("Saliendo del sistema. ¡Gracias por preferir el Teatro Moro!");
                    break;
                } else {
                    System.out.println("Opción inválida. Debe ser 1 o 2.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                scanner.next(); 
            }
        }

        scanner.close();
    }
}

