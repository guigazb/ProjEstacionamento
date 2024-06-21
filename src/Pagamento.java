import java.time.LocalDateTime;

public class Pagamento {
    private Controle tempo;
    private double valorPago;
    private String formaPagamento;
    private LocalDateTime horarioPagamento;

    public Pagamento(Controle tempo, double valorPago, String formaPagamento) {
        this.tempo = tempo;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.horarioPagamento = LocalDateTime.now();
    }

    public void emitirRecibo() {
        System.out.println("Recibo:");
        System.out.println("Veículo: " + tempo.getVeiculo().getPlaca());
        System.out.println("Valor Pago: " + valorPago);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("Horário de Pagamento: " + horarioPagamento);
    }

    public double getValorPago() { return valorPago; }
    public String getFormaPagamento() { return formaPagamento; }
    public LocalDateTime getHorarioPagamento() { return horarioPagamento; }
    public Controle gettempo() { return tempo; }
}
