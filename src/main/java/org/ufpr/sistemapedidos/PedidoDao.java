package org.ufpr.sistemapedidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDao
{
    private final String stmtIncluir = "INSERT INTO pedido(id_cliente, data_pedido) VALUES (?, ?);";
    
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
}
