package org.ufpr.sistemapedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao
{
    
    private final String stmtIncluir = "INSERT INTO produto (descricao) VALUES (?);";
    private final String stmtAlterar = "UPDATE produto SET descricao = ? WHERE id_produto = ?;";
    private final String stmtRemoverProduto = "DELETE FROM produto WHERE id_produto = ?";
    private final String stmtConsultarId = "SELECT * FROM  produto WHERE id_produto = ?;";
    private final String stmtListarProdutos = "SELECT * FROM produto;";
    private final String stmtBuscarProdutos = "SELECT * FROM produto WHERE MATCH(descricao) AGAINST(? IN NATURAL LANGUAGE MODE);";
    
    public void incluirProduto(Produto produto) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtIncluir, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, produto.getDescricao());
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        produto.setId(rs.getInt(1));
        
        con.commit();
        stmt.close();
        con.close();
    }
    public void alterarDescricao(int id, String nova_descricao) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtAlterar);
        stmt.setString(1, nova_descricao);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        
        con.commit();
        stmt.close();
        con.close();
    }
    public void removerProduto(int id) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtRemoverProduto);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        
        con.commit();
        stmt.close();
        con.close();
    }
    public Produto consultarId(int id) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtConsultarId);
        
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        rs.next();
        
        Produto produto = new Produto(rs.getString("descricao"));
        produto.setId(id);
        
        stmt.close();
        con.close();
        
        return produto;
    }
    public List<Produto> listarProdutos() throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtListarProdutos);
        rs = stmt.executeQuery();
        List<Produto> listaProdutos = new ArrayList<Produto>();
        
        while(rs.next())
        {
            Produto produto = new Produto(rs.getString("descricao"));
            produto.setId(rs.getInt("id_produto"));
            listaProdutos.add(produto);
        }
        
        stmt.close();
        con.close();
        
        return listaProdutos;
    }
    public List<Produto> buscarProdutos(String busca) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtBuscarProdutos);
        stmt.setString(1, busca);
        rs = stmt.executeQuery();
        List<Produto> listaProdutos = new ArrayList<Produto>();
        
        while(rs.next())
        {
            Produto produto = new Produto(rs.getString("descricao"));
            produto.setId(rs.getInt("id_produto"));
            listaProdutos.add(produto);
        }
        
        stmt.close();
        con.close();
        
        return listaProdutos;
    }
}
