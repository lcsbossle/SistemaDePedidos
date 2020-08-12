package org.ufpr.sistemapedidos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TesteVenda {

    public static void main(String[] args) throws SQLException {
//        Teste de incluirCliente

//        Cliente cliente = new Cliente("Maria", "Do Carmo", "000.000.000-00");
//        ClienteDao dao = new ClienteDao();
//        dao.incluirCliente(cliente);
//        System.out.println("Adicionadah");
//
//        dao.excluirCliente(cliente);
//        System.out.println("Removidah");

//        Teste de alterarCliente

//        ClienteDao dao = new ClienteDao();
//        dao.alterarCpf(2, "077.814.889-03");
//        dao.alterarNome(2, "Roupas");
//        dao.alterarSobrenome(2, "Roles");
        
//        ClienteDao dao = new ClienteDao();
//        List<Cliente> lista = dao.listarClientes();
//        for(Cliente cliente:lista)
//        {
//            System.out.print(cliente.getId() + "; ");
//            System.out.print(cliente.getCpf() + "; ");
//            System.out.print(cliente.getNome()+ "; ");
//            System.out.print(cliente.getSobreNome()+ "\n");
//        }
//        Teste consultar ID
//        ClienteDao dao = new ClienteDao();
//        Cliente cliente = dao.consultarId(5);
//        System.out.println(cliente.getCpf());
        

//            Teste incluir produto
//        Produto frango = new Produto("Frango bovino");
//        Produto colchao = new Produto("Colchão inflamável multiuso para casal");
//        Produto linguica = new Produto("Linguiça alemã");
//        Produto coelho = new Produto("Coelho caramujo");
//        
//        ProdutoDao dao = new ProdutoDao();
//        
//        dao.incluirProduto(frango);
//        dao.incluirProduto(colchao);
//        dao.incluirProduto(linguica);
//        dao.incluirProduto(coelho);

//        Teste alterar produto
//        ProdutoDao dao = new ProdutoDao();
//        dao.alterarDescricao(1, "Frango perdidão");
//        dao.incluirProduto(new Produto("Creme dental máxima proteção anticaspa"));
//        System.out.println("Inclusoh");
//        dao.removerProduto(5);
//        Produto frango = dao.consultarId(4);
//        System.out.println(frango.getDescricao());

//        ProdutoDao dao = new ProdutoDao();
//        List<Produto> lista = dao.buscarProdutos("casal");
//        for(Produto produto:lista)
//        {
//            System.out.print(produto.getId() + "; ");
//            System.out.print(produto.getDescricao()+ "\n");
//        }
        PedidoDao pedido_dao = new PedidoDao();
        ClienteDao cliente_dao = new ClienteDao();
        ProdutoDao produto_dao = new ProdutoDao();
        
        List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
        itens.add(new ItemDoPedido(10, produto_dao.consultarId(1)));
        itens.add(new ItemDoPedido(20, produto_dao.consultarId(2)));
        itens.add(new ItemDoPedido(30, produto_dao.consultarId(3)));
        itens.add(new ItemDoPedido(40, produto_dao.consultarId(4)));
        
        
        Cliente cliente = cliente_dao.consultarId(1);
        Pedido pedido = new Pedido(cliente, itens);
        
        pedido_dao.incluirPedido(pedido);
    }
}
