public abstract class OpcaoPagamento {
    protected int preco;
    
    public OpcaoPagamento(int preco) {
        this.preco = preco;
    }

    public abstract void pagar();
    
}
