// MainApp.java
// Esta es la clase principal que corre en la consola y maneja el menú, registro, login, OTP, ingreso/gasto, IA, alertas y archivos.

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class MainApp {

    // Estructuras para guardar usuarios temporales (simulación)
    private static Map<String, String> usuarios = new HashMap<>(); // correo -> contraseña
    private static Map<String, String> otpUsuarios = new HashMap<>(); // correo -> OTP generado

    private static Scanner sc = new Scanner(System.in);

    // Presupuesto simulado por usuario (en la vida real podrías tener más datos)
    private static Map<String, Double> presupuestoUsuario = new HashMap<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("===== FINDSAFE (versión consola) =====");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarse();
                    break;
                case "2":
                    iniciarSesion();
                    break;
                case "3":
                    salir = true;
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }

        sc.close();
    }

    // Método para registro de usuario
    private static void registrarse() {
        System.out.println("--- Registro de usuario ---");

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine(); // lo podemos usar luego si queremos

        System.out.print("Número de cédula: ");
        String cedula = sc.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = sc.nextLine();

        System.out.print("Correo electrónico: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        // Generar OTP
        String otp = MotorIA.generarOTP();
        otpUsuarios.put(correo, otp);

        System.out.println("Generando código de verificación...");
        System.out.println("Tu código OTP (simulado) es: " + otp);

        System.out.print("Ingresa el código OTP: ");
        String otpIngresado = sc.nextLine();

        if (!otpIngresado.equals(otp)) {
            System.out.println("OTP incorrecto. El registro falló.");
            return;
        }

        // Guardar usuario
        usuarios.put(correo, contrasena);
        System.out.println("¡Registro exitoso!");

        // Pedir presupuesto inicial
        System.out.print("Ingresa tu presupuesto mensual estimado: ");
        double presupuesto = Double.parseDouble(sc.nextLine());
        presupuestoUsuario.put(correo, presupuesto);

        // Guardamos usuario en archivo para persistencia simple
        guardarUsuarioEnArchivo(correo, cedula, fechaNacimiento);
    }

    // Método para iniciar sesión
    private static void iniciarSesion() {
        System.out.println("--- Iniciar sesión ---");
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        if (!usuarios.containsKey(correo) || !usuarios.get(correo).equals(contrasena)) {
            System.out.println("Correo o contraseña incorrectos.");
            return;
        }

        // Validación OTP para login también
        String otp = MotorIA.generarOTP();
        otpUsuarios.put(correo, otp);
        System.out.println("OTP para verificación de inicio de sesión: " + otp);
        System.out.print("Ingresa el OTP: ");
        String otpLogin = sc.nextLine();
        if (!otpLogin.equals(otp)) {
            System.out.println("OTP inválido. No puedes iniciar sesión.");
            return;
        }

        System.out.println("¡Inicio de sesión exitoso!");

        // Ir al menú interno de la billetera
        menuBilletera(correo);
    }

    // Menú después de iniciar sesión
    private static void menuBilletera(String correo) {
        boolean cerrar = false;

        while (!cerrar) {
            System.out.println("\n--- BILLETERA DIGITAL ---");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Ver historial");
            System.out.println("4. IA: Consejos financieros");
            System.out.println("5. Alertas automáticas");
            System.out.println("6. Cerrar sesión");
            System.out.print("Selecciona una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarIngreso(correo);
                    break;
                case "2":
                    registrarGasto(correo);
                    break;
                case "3":
                    verHistorial(correo);
                    break;
                case "4":
                    usarIA(correo);
                    break;
                case "5":
                    mostrarAlerta(correo);
                    break;
                case "6":
                    cerrar = true;
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
                    break;
            }
        }
    }

    // Método para registrar ingreso
    private static void registrarIngreso(String correo) {
        System.out.print("Monto del ingreso: ");
        double monto = Double.parseDouble(sc.nextLine());
        System.out.print("Descripción del ingreso: ");
        String descripcion = sc.nextLine();

        // Guardar en archivo
        guardarLineaArchivo(correo + "_ingresos.txt",
                "INGRESO - " + monto + " - " + descripcion);

        System.out.println("Ingreso registrado exitosamente.");
    }

    // Método para registrar gasto
    private static void registrarGasto(String correo) {
        System.out.print("Valor del gasto: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Descripción del gasto: ");
        String descripcion = sc.nextLine();

        // Clasificar el gasto con IA
        String tipo = MotorIA.clasificarGasto(descripcion);
        String riesgo = MotorIA.evaluarRiesgo(valor, presupuestoUsuario.get(correo));

        // Guardar en archivo
        guardarLineaArchivo(correo + "_gastos.txt",
                "GASTO (" + tipo + ") - " + valor + " - " + descripcion
                        + " - Riesgo: " + riesgo);

        System.out.println("Gasto registrado como: " + tipo);
        System.out.println("Nivel de riesgo: " + riesgo);
    }

    // Método para ver historial
    private static void verHistorial(String correo) {
        System.out.println("\n--- HISTORIAL DE INGRESOS ---");
        leerArchivo(correo + "_ingresos.txt");

        System.out.println("\n--- HISTORIAL DE GASTOS ---");
        leerArchivo(correo + "_gastos.txt");
    }

    // Método para usar la IA para consejos
    private static void usarIA(String correo) {
        System.out.print("Escribe lo que quieras preguntarle a la IA: ");
        String mensaje = sc.nextLine();

        String categoria = MotorIA.detectarCategoria(mensaje);
        String respuesta = MotorIA.generarRespuesta(categoria);

        System.out.println("IA: " + respuesta);
    }

    // Método para mostrar alerta según el gasto total vs presupuesto
    private static void mostrarAlerta(String correo) {
        // Leer todos los gastos
        double sumaGastos = sumarArchivoValores(correo + "_gastos.txt");
        double presupuesto = presupuestoUsuario.get(correo);

        double porcentaje = (sumaGastos / presupuesto) * 100;

        System.out.println("Has gastado: " + sumaGastos + " de tu presupuesto de " + presupuesto);
        System.out.printf("Porcentaje gastado: %.2f%%\n", porcentaje);

        if (porcentaje >= 100) {
            System.out.println("⚠️ ALERTA CRÍTICA: Has superado o igualado tu presupuesto.");
        } else if (porcentaje >= 80) {
            System.out.println("⚠️ ALERTA ALTA: Has superado el 80% de tu presupuesto.");
        } else if (porcentaje >= 60) {
            System.out.println("⚠️ ALERTA MEDIA: Has superado el 60% de tu presupuesto.");
        } else {
            System.out.println("Todo bien por ahora. ¡Sigue así!");
        }
    }

    // Función para guardar un usuario en un archivo (persistencia simple)
    private static void guardarUsuarioEnArchivo(String correo, String cedula, String fechaNacimiento) {
        String linea = "USUARIO - Correo: " + correo + " - Cédula: " + cedula + " - Fecha Nac: " + fechaNacimiento;
        guardarLineaArchivo("usuarios.txt", linea);
    }

    // Función genérica para guardar una línea en un archivo
    private static void guardarLineaArchivo(String nombreArchivo, String linea) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(linea);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    // Función para leer y mostrar un archivo línea por línea
    private static void leerArchivo(String nombreArchivo) {
        try (FileReader fr = new FileReader(nombreArchivo);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo o no existe: " + nombreArchivo);
        }
    }

    // Suma todos los valores de los gastos guardados en el archivo para alertas
    private static double sumarArchivoValores(String nombreArchivo) {
        double suma = 0.0;
        try (FileReader fr = new FileReader(nombreArchivo);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Esperamos que la línea tenga formato: "GASTO (...) - valor - descripción - Riesgo: ..."
                String[] partes = linea.split(" - ");
                if (partes.length >= 3) {
                    String valorString = partes[1].trim();
                    try {
                        double valor = Double.parseDouble(valorString);
                        suma += valor;
                    } catch (NumberFormatException ex) {
                        // Si no se puede parsear, se ignora esa línea
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo para sumar valores: " + e.getMessage());
        }
        return suma;
    }
}
