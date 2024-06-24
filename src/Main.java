import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final ArrayList<String> opcoes = new ArrayList<>() {{
        add("Cadastrar nova vaga");
        add("Deletar vaga");
        add("Sair");
    }};

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Database.iniciar();
        MainWindow.run();

        while (true) {
            System.out.println("Escolha uma opcao abaixo: ");
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.printf("\t%d. %s\n", i+1, opcoes.get(i));
            }
            int op = leInt("-> ");

            int num;
            String loc, veic;
            Vaga v;
            
            switch (op) {
            case 1:
                num = leInt("Insira o numero da vaga: ");
                loc = leString("Insira a localizaçao da vaga: ");
                veic = leString("Insira o tipo de veiculo: ");
                v = new Vaga(num, loc, veic);
                Database.addVaga(v);
                break;

            case 2:
                num = leInt("Insira o numero da vaga: ");
                loc = leString("Insira a localizaçao da vaga a ser apagada: ");
                v = Database.getVaga(num, loc);
                if (v == null) {
                    System.out.println("Vaga não encontrada");
                } else {
                    Database.rmvVaga(v);
                }
                break;

            case 3:
                Database.fechar();
                System.exit(0);
                break;

            default:
            }
        }
    }

    public static int leInt(String prompt) {
        Integer x = null;
        while (x == null) {
            try {
                System.out.print(prompt);
                x = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Entrada invalida!");
                x = null;
            }
        }
        sc.nextLine();
        return x;
    }

    public static String leString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
