public class Moto extends Veiculo {
    private String marca;
    private int cilindradas;

    public Moto(String placa, String marca, int cilindradas) {
        super(placa, "Moto");
        this.marca = marca;
        this.cilindradas = cilindradas;
    }

    public String getMarca() { return this.marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public double getCilindradas() { return this.cilindradas; }
    public void setCilindradas(int cilindradas) { this.cilindradas = cilindradas; }

}