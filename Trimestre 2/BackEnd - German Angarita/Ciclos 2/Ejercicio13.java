import java.util.Scanner;

public class Ejercicio13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la frase: ");
        String frase = scanner.nextLine().toLowerCase();

        int i = 0;
        int contador = 0;
        String vocales = "";

        // .length() devuelve la cantidad de caracteres (letras, espacios, signos, etc.) que tiene la frase
        while (i < frase.length()) {

            // .substring(inicio, fin) devuelve una parte del texto
            // En este caso, toma una sola letra desde la posición i hasta i+1
            String letra = frase.substring(i, i + 1);

            // .equals() se usa para comparar cadenas de texto (String)
            if (letra.equals("a") || letra.equals("e") || letra.equals("i") || letra.equals("o") || letra.equals("u")) {
                contador++; 
                vocales += letra + " ";
            }

            i++;

        }

           // Se escribe "vocal(es)" para que funcione tanto en singular es decir si sale una vocal dira la frase tiene 1 vocal o si tiene varias dira tiene 4 vocales
        System.out.println("La frase tiene " + contador + " vocal(es)");
        System.out.println("Vocales encontrada:" + vocales);
        scanner.close();
    }
}
