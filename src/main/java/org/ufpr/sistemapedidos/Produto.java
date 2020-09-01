package org.ufpr.sistemapedidos;

public class Produto
{
    private int id;
    private String descricao;
    
    public Produto(String descricao)
    {
        this.id = id;
        this.descricao = descricao;
    }
    
    public Produto(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
