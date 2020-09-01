package org.ufpr.sistemapedidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao
{
    private final String stmtIncluir = "INSERT INTO pedido(id_cliente, data_pedido) VALUES (?, ?);";
    private final String stmtListarPedidos = "SELECT * FROM pedido WHERE id_cliente = ?";
    private final String stmtListarItens = "SELECT * FROM item_do_pedido WHERE id_pedido = ?";
    
    public void incluirPedido (Pedido pedido) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtIncluir, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pedido.getCliente().getId());
        stmt.setDate(2, Date.valueOf(pedido.getData()));
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        pedido.setId(rs.getInt(1));
        
        this.gravarItensDePedido(pedido, con);
        
        con.commit();
        stmt.close();
        con.close();
    }
    public void gravarItensDePedido (Pedido pedido, Connection con) throws SQLException
    {
        String sql = "INSERT INTO item_do_pedido (id_pedido, id_produto, qtdade) VALUE (?,?,?);";
        PreparedStatement stmt;
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, pedido.getId());
        
        List<ItemDoPedido> itens = pedido.getItens();
        for (ItemDoPedido item : itens)
        {
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.executeUpdate();
        }
    }
    
    public List<ItemDoPedido> listarItensDoPedido (Pedido pedido) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtListarItens);
        stmt.setInt(1, pedido.getId());
        rs = stmt.executeQuery();
        List<ItemDoPedido> listaItens = new ArrayList();
        
        while(rs.next())
        {
            Produto produto = new Produto(rs.getInt("id_produto"));
            ItemDoPedido item = new ItemDoPedido(rs.getInt("qtdade"), produto);
            listaItens.add(item);
        }
        
        stmt.close();
        con.close();
        
        ProdutoDao produtoDao = new ProdutoDao();
        for(ItemDoPedido item : listaItens)
        {
            item.getProduto().setDescricao(produtoDao.consultarId(item.getProduto().getId()).getDescricao());
        }
        
        return listaItens;
    }
    
    public List<Pedido> listarPedidosDeCliente(Cliente cliente) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtListarPedidos);
        stmt.setInt(1, cliente.getId());
        rs = stmt.executeQuery();
        List<Pedido> listaPedidos = new ArrayList();
        
        while(rs.next())
        {
            Integer id_cliente = rs.getInt("id_cliente");
            Integer id_pedido = rs.getInt("id_pedido");
            LocalDate data = rs.getDate("data_pedido").toLocalDate();
            Pedido pedido = new Pedido(id_pedido, cliente, data);
            listaPedidos.add(pedido);
        }
        
        stmt.close();
        con.close();
        
        for(Pedido pedido : listaPedidos)
        {
            List<ItemDoPedido> listaItens = this.listarItensDoPedido(pedido);
            pedido.setItens(listaItens);
        }
        
        return listaPedidos;
    }
}
