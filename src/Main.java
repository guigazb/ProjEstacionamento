import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // TODO: relatorio de ocupacao
    // TODO: permitir consulta de entrada e saida de veiculos
    // TODO: permitir consulta e atualizacao dos dados dos clientes
    // TODO: sistema de reservamento para reservar uma vaga em um dado horario e nao permitir que ela seja ocupada ate la e depois liberala

    private static final String[] menuPrincipalOpcoes = {
        "Fechar Sistema",
        "Controle de Vagas",
        "Relatorio de Pagamentos"
    };

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Database.iniciar();
        MainWindow.run();

        while (true) {
            System.out.println("Escolha uma opcao abaixo: ");
            for (int i = 0; i < menuPrincipalOpcoes.length; i++) {
                System.out.printf("\t%d. %s\n", i+1, menuPrincipalOpcoes[i]);
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

            case 3: // Relatorio de Pagamentos
                menuRelatorioPagamentos();
                break;

            default:
                System.out.println("Operaçao invalida!");
            }
        }
    }

    private static final String[] menuVagasOpcoes = {
        "Voltar ao menu principal",
        "Cadastrar nova vaga",
        "Remover vaga",
        "Listar vagas existentes"
    };

    private static void menuVagas() {
        boolean r = true;
        while (r) {
            System.out.println("Controle de Vagas:");
            for (int i = 0; i < menuVagasOpcoes.length; i++) {
                System.out.printf("\t%d. %s\n", i+1, menuVagasOpcoes[i]);
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

    private static final String[] menuRelatorioOpcoes = {
        "Voltar ao menu principal",
        "Gerar relatorio Historico",
        "Gerar relatorio Diario"
    };

    private static void menuRelatorioPagamentos() {
        boolean r = true;
        while (r) {
            System.out.println("Menu Relatorio:");
            for (int i = 0; i < menuRelatorioOpcoes.length; i++) {
                System.out.printf("\t%d. %s\n", i+1, menuRelatorioOpcoes[i]);
            }

            int op = leInt("-> ");
            ArrayList<Pagamento> pags;
            double total;
            int i;

            switch (op) {
            case 1: // Menu Principal
                r = false;
                break;
            case 2: // Historico
                pags = Database.getHistoricoDePagamentos();
                i = 1;
                total = 0;
                for (Pagamento p : pags) {
                    System.out.printf("Pagamento %d\n", i);
                    System.out.printf("\t- Valor: R$%f\n", (float)p.getValorPago());
                    System.out.printf("\t- Forma de Pagamento: %s\n", p.getFormaPagamento());
                    System.out.printf("\t- Horario de Pagamento: %s\n", p.getHorarioPagamento().toString());
                    total += p.getValorPago();
                    i++;
                }
                System.out.printf("Total Arrecadado: R$%f\n", (float)total);
                break;
            case 3: // Diario
                pags = Database.getPagamentosDiario();
                i = 1;
                total = 0;
                for (Pagamento p : pags) {
                    System.out.printf("Pagamento %d\n", i);
                    System.out.printf("\t- Valor: R$%f\n", (float)p.getValorPago());
                    System.out.printf("\t- Forma de Pagamento: %s\n", p.getFormaPagamento());
                    System.out.printf("\t- Horario de Pagamento: %s\n", p.getHorarioPagamento().toString());
                    total += p.getValorPago();
                    i++;
                }
                System.out.printf("Total Arrecadado: R$f\n", (float)total);
                break;
            default: 
                System.out.println("Operacao invalida!");
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
