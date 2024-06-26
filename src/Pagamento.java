import java.time.LocalDateTime;

public class Pagamento {
    // private Controle tempo;
    private double valorPago;
    private String formaPagamento;
    private LocalDateTime horarioPagamento;

    public Pagamento(double valorPago, String formaPagamento, LocalDateTime horarioPagamento) {
        // this.tempo = tempo;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.horarioPagamento = horarioPagamento;
        Database.addPagamento(this);
    }

    public Pagamento(double valorPago, String formaPagamento) {
        new Pagamento(valorPago, formaPagamento, LocalDateTime.now());
    }

    /* 
    public void emitirRecibo() {
        System.out.println("Recibo:");
        System.out.println("Veículo: " + tempo.getVeiculo().getPlaca());
        System.out.println("Valor Pago: " + valorPago);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("Horário de Pagamento: " + horarioPagamento);
    }
    public Controle getTempo() { return tempo; }
    */

    public double getValorPago() { return valorPago; }
    public String getFormaPagamento() { return formaPagamento; }
    public LocalDateTime getHorarioPagamento() { return horarioPagamento; }
}
