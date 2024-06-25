public abstract class Relatorio {
    public static void gerarRelatorio() {
        System.out.println("Relatório:");
        for (Vaga vaga : Database.getVagaList()) {
            System.out.println("Vaga " + vaga.getNumero() + ": " + vaga.getStatus());
        }
    }

    public static void gerarRelatorioDiario() {
        double total = 0;
        for (Pagamento pagamento : Database.getHistoricoDePagamentos()) {
            total += pagamento.getValorPago();
        }
        System.out.println("Total arrecadado no dia: " + total);
    }
}
