import java.util.List;

public abstract class Relatorio {
    public static void gerarRelatorio(List<Vaga> vagas) {
        System.out.println("Relatório:");
        for (Vaga vaga : vagas) {
            String livre = (vaga.estaLivre()) ? "livre" : "ocupada";
            System.out.println("Vaga " + vaga.getNumero() + ": " + livre);
        }
    }

    public static void gerarRelatorioDiario(List<Pagamento> pagamentos) {
        double total = 0;
        for (Pagamento pagamento : pagamentos) {
            total += pagamento.getValorPago();
        }
        System.out.println("Total arrecadado no dia: " + total);
    }
}
