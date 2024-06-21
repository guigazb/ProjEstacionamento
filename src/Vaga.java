public class Vaga {
    private int numero;
    private String localizacao;
    private String status;
    private String tipoVeiculo; 

    public Vaga(int numero, String localizacao, String tipoVeiculo) {
        this.numero = numero;
        this.localizacao = localizacao;
        this.tipoVeiculo = tipoVeiculo;
        this.status = "livre";
    }

    public int getNumero() { return numero; }

    public String getLocalizacao() { return localizacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipoVeiculo() { return tipoVeiculo; }
}
