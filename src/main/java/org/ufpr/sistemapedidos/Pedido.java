package org.ufpr.sistemapedidos;

//import java.util.Date;
import java.util.List;
import java.time.LocalDate;

public class Pedido
{
    private int id;
    private LocalDate data;
    private Cliente cliente;
    private List<ItemDoPedido> itens;
    
    public Pedido(int id, Cliente cliente, List<ItemDoPedido> itens, LocalDate data)
    {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.data = data;
    }
    public Pedido(Cliente cliente, List<ItemDoPedido> itens, LocalDate data)
    {
        this.cliente = cliente;
        this.itens = itens;
        this.data = data;
    }
    public Pedido(Cliente cliente, List<ItemDoPedido> itens)
    {
        this.cliente = cliente;
        this.itens = itens;
        this.data = LocalDate.now();
    }
    public Pedido(Cliente cliente)
    {
        this.cliente = cliente;
        this.data = LocalDate.now();
    }
    public Pedido(Integer id, Cliente cliente, LocalDate data)
    {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
    }
    
    public Pedido(){
        this.data = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDoPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedido> itens) {
        this.itens = itens;
    }
    
}
