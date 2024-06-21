public class Carro extends Veiculo {
    private String cor;
    private String marca;
    private String modelo;

    public Carro(String placa, String cor, String marca, String modelo) {
        super(placa, "Carro");
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getCor() { return this.cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getMarca() { return this.marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return this.modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
}