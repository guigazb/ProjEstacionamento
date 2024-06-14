public abstract class OpcaoPagamento {
    protected int valor;
    
    public OpcaoPagamento(int valor) {
        this.valor = valor;
    }

    public abstract void pagar(Boleto b);
    public int getValor() { return this.valor; }
    
    // TODO: Implementar diferentes formas de pagamento (Dinheiro, Cartao e Pix)
}
