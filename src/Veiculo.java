public abstract class Veiculo {
    protected String placa;
    protected String tipo;

    public Veiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() { return placa; }
    public String getTipo() { return tipo; }
}
