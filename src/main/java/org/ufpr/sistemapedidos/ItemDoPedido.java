package org.ufpr.sistemapedidos;

public class ItemDoPedido
{
    private int quantidade;
    private Produto produto;
    
    public ItemDoPedido(int quantidade, Produto produto)
    {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}