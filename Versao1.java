// Primeira versão

import java.util.Scanner;

public class Versao1 {

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número inicial do intervalo: ");
        int inicio = scanner.nextInt();

        System.out.print("Informe o número final do intervalo: ");
        int fim = scanner.nextInt();

        if (inicio > fim) 
        {
            System.out.println("Intervalo inválido! O número inicial deve ser menor ou igual ao final.");
        } else {
            System.out.println("Números primos entre " + inicio + " e " + fim + ":");

            int contador = 0;
            for (int i = inicio; i <= fim; i++) 
            {
                if (ehPrimo(i)) 
                {
                    System.out.print(i + " ");
                    contador++;
                }
            }

            System.out.println("\nTotal de primos encontrados: " + contador);
        }

        scanner.close();
    }

    public static boolean ehPrimo(int n) 
    {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) 
        {
            if (n % i == 0) return false;
        }
        return true;
    }
}
