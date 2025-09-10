// Quinta versão

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Versao5 
{
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
            scanner.close();
            return;
        }

        List<Thread> threads = new ArrayList<>();
        List<List<Integer>> listasPrimosPorThread = new ArrayList<>();

        int atualInicio = inicio;
        int threadNum = 1;

        while (atualInicio <= fim) 
        {
            int atualFim = Math.min(atualInicio + tamanhoSubIntervalo - 1, fim);

            int finalAtualInicio = atualInicio;
            int finalAtualFim = atualFim;
            int finalThreadNum = threadNum;

            List<Integer> primosLocal = new ArrayList<>();
            listasPrimosPorThread.add(primosLocal);

            Thread t = new Thread(() -> 
            {
                System.out.println("Thread" + finalThreadNum + " -> sub-intervalo " + finalAtualInicio + " a " + finalAtualFim);
                for (int i = finalAtualInicio; i <= finalAtualFim; i++) 
                {
                    if (ehPrimo(i)) 
                    {
                        primosLocal.add(i);
                    }
                }
            });

            threads.add(t);
            t.start();

            atualInicio = atualFim + 1;
            threadNum++;
        }

        // Espera todas as threads terminarem
        for (Thread t : threads) 
        {
            try 
            {
                t.join();
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        List<Integer> primosGlobais = new ArrayList<>();
        for (List<Integer> lista : listasPrimosPorThread) 
        {
            primosGlobais.addAll(lista);
        }

        Collections.sort(primosGlobais);

        System.out.println("Números primos encontrados (ordenados):");
        for (int i = 0; i < primosGlobais.size(); i++) 
        {
            System.out.println((i + 1) + ": " + primosGlobais.get(i));
        }

        System.out.println("Total de primos encontrados: " + primosGlobais.size());

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
