import java.util.Scanner;

public class Ingresos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ingresosDia[] = new int[7];
        boolean menu = true;
        int ingresoMenor = 0;
        int ingresoMayor = 0;
        double promedio = 0;

        while (menu) {
            System.out.println("--------Bienvenidos a UlalaFind--------");
            System.out.println("--------¿Qué desea realizar?--------\n");
            System.out.println("1. Ingresar ingresos diarios");
            System.out.println("2. Mostrar promedio de ingresos");
            System.out.println("3. Mostrar ingreso menor");
            System.out.println("4. Mostrar ingreso mayor");
            System.out.println("5. Salir");

            int opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    for (int i = 0; i < ingresosDia.length; i++) {
                        System.out.println("Ingrese el ingreso del día " + (i + 1));
                        ingresosDia[i] = sc.nextInt();
                    }
                    break;

                case 2:
                    double suma = 0;
                    for (int i = 0; i < ingresosDia.length; i++) {
                        suma += ingresosDia[i];
                    }
                    promedio = suma / ingresosDia.length;
                    System.out.println("El promedio de ingresos es: " + promedio);
                    break;

                case 3:
                    ingresoMenor = ingresosDia[0];
                    for (int i = 1; i < ingresosDia.length; i++) {
                        if (ingresosDia[i] < ingresoMenor) {
                            ingresoMenor = ingresosDia[i];
                        }
                    }
                    System.out.println("El ingreso menor es: " + ingresoMenor);
                    break;

                case 4:
                    ingresoMayor = ingresosDia[0];
                    for (int i = 1; i < ingresosDia.length; i++) {
                        if (ingresosDia[i] > ingresoMayor) {
                            ingresoMayor = ingresosDia[i];
                        }
                    }
                    System.out.println("El ingreso mayor es: " + ingresoMayor);
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    menu = false;
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }

        sc.close();
    }
}
