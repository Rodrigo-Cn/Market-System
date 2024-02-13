package br.com.aula.heranca.sistema;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double valor;
    private double subtotal;
    public ItemVenda(){}
    public ItemVenda(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
        produto.setQuantEmEstoque(produto.getQuantEmEstoque()-this.quantidade);
        this.valor = this.produto.getPreco();
        this.subtotal = this.valor*this.quantidade;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
        this.valor = this.produto.getPreco();
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = this.valor*this.quantidade;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public double getSubtotal() {
        return subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Produto: " + produto + ", Quantidade: " + quantidade + ", Valor: " + valor + ", Subtotal: " + subtotal + "R$";
    }
}
