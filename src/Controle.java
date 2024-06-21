import java.time.LocalDateTime;

public class Controle {
    private Veiculo veiculo;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private Vaga vaga;

    public Controle(Veiculo veiculo, Vaga vaga) {
        this.veiculo = veiculo;
        this.horarioEntrada = LocalDateTime.now();
        this.vaga = vaga;
        vaga.setStatus("ocupada");
    }

    public void registrarSaida() {
        this.horarioSaida = LocalDateTime.now();
        vaga.setStatus("livre");
    }

    public long calcularTempoPermanencia() { return java.time.Duration.between(horarioEntrada, horarioSaida).toMinutes(); }

    public Veiculo getVeiculo() { return veiculo; }
    public LocalDateTime getHorarioEntrada() { return horarioEntrada; }
    public LocalDateTime getHorarioSaida() { return horarioSaida; }
    public Vaga getVaga() { return vaga; }
}
