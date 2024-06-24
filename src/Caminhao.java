public class Caminhao extends Veiculo {
    private double cargaMaxima;
    private double comprimento;

    public Caminhao(String placa, double cargaMaxima, double comprimento) {
        super(placa, "Caminhao");
        this.cargaMaxima = cargaMaxima;
        this.comprimento = comprimento;
    }

    public double getCargaMaxima() { return this.cargaMaxima; }
    public void setCargaMaxima(double carga) { this.cargaMaxima = carga; }
    
    public double getComprimento() { return this.comprimento; }
    public void setComprimento(double comprimento) { this.comprimento = comprimento; }
    
}