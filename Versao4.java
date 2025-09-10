// Quarta versão

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Versao4 
{
    @SuppressWarnings("resource")
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o início do intervalo: ");
        int inicio = scanner.nextInt();

        System.out.print("Informe o fim do intervalo: ");

        int fim = scanner.nextInt();

        System.out.print("Informe o tamanho do sub-intervalo: ");
        int tamanhoSubIntervalo = scanner.nextInt();

        if (inicio > fim || tamanhoSubIntervalo <= 0) 
        {
            System.out.println("Parâmetros inválidos!");
            return;
        }

        List<Thread> threads = new ArrayList<>();
        final int[] totalPrimos = {0};

        int atualInicio = inicio;
        int threadNum = 1;

        while (atualInicio <= fim) 
        {
            int atualFim = Math.min(atualInicio + tamanhoSubIntervalo - 1, fim);

            int finalAtualInicio = atualInicio;
            int finalAtualFim = atualFim;
            int finalThreadNum = threadNum;

            Thread t = new Thread(() -> 
            {
                System.out.println("Thread" + finalThreadNum + " -> sub-intervalo " + finalAtualInicio + " a " + finalAtualFim);
                int contadorLocal = 0;
                for (int i = finalAtualInicio; i <= finalAtualFim; i++) 
                {
                    if (ehPrimo(i)) {
                        contadorLocal++;
                    }
                }
                synchronized (totalPrimos) 
                {
                    totalPrimos[0] += contadorLocal;
                }
            });

            threads.add(t);
            t.start();

            atualInicio = atualFim + 1;
            threadNum++;
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        System.out.println("Total de primos encontrados: " + totalPrimos[0]);
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
