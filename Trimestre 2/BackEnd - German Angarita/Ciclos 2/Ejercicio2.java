import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int contador = 60;

        while (contador >=10 ){
            System.out.println(" " + contador);
            contador--;
        }
        scanner.close();

    }
}
