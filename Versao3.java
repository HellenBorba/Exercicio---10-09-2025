// Terceira versão

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Versao3 
{
    @SuppressWarnings("resource")
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número inicial do intervalo: ");
        int inicio = scanner.nextInt();

        System.out.print("Informe o número final do intervalo: ");
        int fim = scanner.nextInt();

        if (inicio > fim) 
        {
            System.out.println("Intervalo inválido! O início deve ser menor ou igual ao fim.");
            return;
        }

        int meio = (inicio + fim) / 2;

        AtomicInteger totalPrimos = new AtomicInteger(0); 

        Thread thread1 = new Thread(() -> 
        {
            for (int i = inicio; i <= meio; i++) 
            {
                if (ehPrimo(i)) {
                    System.out.println("[Thread 1] Primo encontrado: " + i);
                    totalPrimos.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = meio + 1; i <= fim; i++) 
            {
                if (ehPrimo(i)) {
                    System.out.println("[Thread 2] Primo encontrado: " + i);
                    totalPrimos.incrementAndGet();
                }
            }
        });

        long tempoInicio = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try 
        {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        long tempoFim = System.currentTimeMillis();

        System.out.println("Total de primos encontrados: " + totalPrimos.get());
        System.out.println("Tempo de execução: " + (tempoFim - tempoInicio) + " ms");

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
