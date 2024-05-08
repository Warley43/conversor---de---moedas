import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        System.out.println("******************************************************************");
        System.out.println("Bem vindo ao Conversor de Moedas");

        while (!sair) {
            System.out.println("Selecione a opção desejada:");
            System.out.println("1) Dólar           ---> Peso argentino");
            System.out.println("2) Peso argentino  ---> Dólar");
            System.out.println("3) Dólar           ---> Real brasileiro");
            System.out.println("4) Real brasileiro ---> Dólar");
            System.out.println("5) Dólar           ---> Peso colombiano");
            System.out.println("6) Peso colombiano ---> Dólar");
            System.out.println("7) Sair");
            System.out.println("**************************************************************");

            System.out.println("Escolha uma opção:");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    converterMoedas(scanner, "USD", "ARS");
                    break;
                case 2:
                    converterMoedas(scanner, "ARS", "USD");
                    break;
                case 3:
                    converterMoedas(scanner, "USD", "BRL");
                    break;
                case 4:
                    converterMoedas(scanner, "BRL", "USD");
                    break;
                case 5:
                    converterMoedas(scanner, "USD", "COP");
                    break;
                case 6:
                    converterMoedas(scanner, "COP", "USD");
                    break;
                case 7:
                    sair = true;
                    System.out.println("encerrando");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    public static void converterMoedas(Scanner scanner, String moedaOrigem, String moedaDestino) {
        System.out.println("Digite o valor que deseja converter:");
        double valor = scanner.nextDouble();

        try {
            double valorConvertido = ConversorMoedas.converterMoeda(valor, moedaOrigem, moedaDestino);
            System.out.println("*******************************************************************");

            System.out.println(valor + " " + moedaOrigem + " equivalem a " + valorConvertido + " " + moedaDestino);

            System.out.println("*******************************************************************");

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }
}
