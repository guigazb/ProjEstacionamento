import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final ArrayList<String> opcoes = new ArrayList<>() {{
        add("Fechar Sistema");
        add("Controle de Vagas");
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
            
            switch (op) {
            case 1: // Fechar
                Database.fechar();
                System.exit(0);
                break;

            case 2: // Controle de Vagas
                menuVagas();
                break;

            default:
                System.out.println("Operaçao invalida!");
            }
        }
    }

    private static final ArrayList<String> menuVagasOpcoes = new ArrayList<>() {{
        add("Voltar ao menu principal");
        add("Cadastrar nova vaga");
        add("Remover vaga");
        add("Listar vagas existentes");
    }};

    private static void menuVagas() {
        boolean r = true;
        while (r) {
            System.out.println("Controle de Vagas:");
            for (int i = 0; i < menuVagasOpcoes.size(); i++) {
                System.out.printf("\t%d. %s\n", i+1, menuVagasOpcoes.get(i));
            }
            int op = leInt("-> ");

            int num;
            String loc, veic;
            Vaga v;

            switch (op) {
            case 1: // Voltar ao menu principal
                r = false;
                break;

            case 2: // Cadastrar nova vaga
                num = leInt("Insira o numero da vaga: ");
                loc = leString("Insira a localizaçao da vaga: ");
                veic = leString("Insira o tipo de veiculo: ");
                v = new Vaga(num, loc, veic);
                if (Database.addVaga(v)) { System.out.println("Vaga adicionada com sucesso!"); }
                else { System.out.println("A vaga adicionada ja existe!"); }
                break;

            case 3: // Remover vaga
                num = leInt("Insira o numero da vaga: ");
                loc = leString("Insira a localizaçao da vaga a ser apagada: ");
                v = Database.getVaga(num, loc);
                if (v == null) {
                    System.out.println("Vaga não encontrada");
                } else {
                    Database.rmvVaga(v);
                }
                break;

            case 4: // Listar vagas
                int i = 1;
                for (Vaga vaga : Database.getVagaList()) {
                    System.out.printf("Vaga %d:\n", i);
                    System.out.printf("\t- Numero: %d\n", vaga.getNumero());
                    System.out.printf("\t- Localizaçao: %s\n", vaga.getLocalizacao());
                    System.out.printf("\t- Status: %s\n", vaga.getStatus());
                    System.out.printf("\t- Tipo de Veiculo: %s\n", vaga.getTipoVeiculo());
                    i++;
                }
                break;

            default:
                System.out.println("Operaçao invalida!");
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
