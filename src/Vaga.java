public class Vaga {
    private int numero;
    private String localizacao;
    private boolean livre;
    private String tipoVeiculo; 

    public Vaga(int numero, String localizacao, String tipoVeiculo) {
        this.numero = numero;
        this.localizacao = localizacao;
        this.tipoVeiculo = tipoVeiculo;
        this.livre = true;
    }

    public int getNumero() { return numero; }

    public String getLocalizacao() { return localizacao; }

    public boolean estaLivre() { return livre; }
    public void setLivre(boolean livre) { this.livre = livre; }

    public String getTipoVeiculo() { return tipoVeiculo; }
}
