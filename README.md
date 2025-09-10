# Exercicio---10-09-2025
Aula 10/09/2025 - Jorge Werner

Desenvolva um programa que dado, um intervalo número, imprime os números primos existentes. Exemplo:
Intervalo:5 a 25
Primos existentes: 5, 7, 11, 13, 17, 19, 23
A ordem de impressão na tela é irrelevante

- Versão 1: Apenas o processo (sem Threads)
- Versão 2: Criar uma Thread que imprime os primos no intervalo
- Versão 3: Criar 2 Threads: [inicio ... metade][metade+1 ... fim]
- Versão 4: Criar várias Threads, uma para cada sub-intervalo. Exemplo:
  - Thread1 -> sub-intervalo 5 a 50
  - Thread2 -> sub-intervalo 51 a 100
  - Thread3 -> sub-intervalo 101 a 150
- Versão 5: Altere a versão anterior para guardar os números primos encontrado em uma lista. Depois de encontrar todos os números primos do intervalo, liste os números ordenados e indexados.

Ao executar estas versões, qual é a mais rápida?
Testar com intervalos grandes. Exemplo> 1 a 1 milhão
Usar Windows PowerShell para medir o tempo de execução.
