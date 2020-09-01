package org.ufpr.sistemapedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    
    private final String stmtIncluir = "INSERT INTO cliente (cpf, nome, sobrenome) VALUES (?, ?, ?);";
    private final String stmtAlterarCpf = "UPDATE cliente SET cpf = ? WHERE id_cliente = ?;";
    private final String stmtAlterarNome = "UPDATE cliente SET nome = ? WHERE id_cliente = ?;";
    private final String stmtAlterarSobrenome = "UPDATE cliente SET sobrenome = ? WHERE id_cliente = ?;";
    private final String stmtListarClientes = "SELECT * from cliente;";
    private final String stmtRemoverCliente = "DELETE FROM cliente WHERE id_cliente = ?";
    private final String stmtConsultarId = "SELECT * FROM cliente WHERE id_cliente = ?";
    private final String stmtBuscarClientes = "SELECT * FROM cliente WHERE MATCH(nome, sobrenome) AGAINST(? IN NATURAL LANGUAGE MODE);";
    private final String stmtConsultarCpf = "SELECT * FROM cliente WHERE cpf = ?";
    
    public List<Cliente> listarClientes() throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtListarClientes);
        rs = stmt.executeQuery();
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        while(rs.next())
        {
            Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("cpf"));
            
            cliente.setId(rs.getInt("id_cliente"));
            
            listaClientes.add(cliente);
        }
        
//        con.commit();
        stmt.close();
        con.close();
        
        return listaClientes;
    }
    
    public void incluirCliente(Cliente cliente) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtIncluir, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getSobreNome());
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        cliente.setId(rs.getInt(1));
        
        con.commit();
        stmt.close();
        con.close();
    }
    public void alterarCpf(int id, String novo_cpf) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtAlterarCpf);
        stmt.setString(1, novo_cpf);
        stmt.setInt(2, id);
        stmt.executeUpdate();

        con.commit();
        stmt.close();
        con.close();
    }
    public void alterarNome(int id, String novo_nome) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtAlterarNome);
        stmt.setString(1, novo_nome);
        stmt.setInt(2, id);
        stmt.executeUpdate();

        con.commit();
        stmt.close();
        con.close();
    }
    public void alterarSobrenome(int id, String novo_sobrenome) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtAlterarSobrenome);
        stmt.setString(1, novo_sobrenome);
        stmt.setInt(2, id);
        stmt.executeUpdate();

        con.commit();
        stmt.close();
        con.close();
    }
    public void excluirCliente(Cliente cliente) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        
        con = ConnectionFactory.getConnection();
        con.setAutoCommit(false);
        
        stmt = con.prepareStatement(stmtRemoverCliente);
        stmt.setInt(1, cliente.getId());
        stmt.executeUpdate();
        
        con.commit();
        stmt.close();
        con.close();
    }
    public Cliente consultarId(int id_cliente) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtConsultarId);
        stmt.setInt(1, id_cliente);
        rs = stmt.executeQuery();
        rs.next();
        
        Cliente cliente = new Cliente(
                rs.getString("nome"),
                rs.getString("sobrenome"),
                rs.getString("cpf"));
        
        cliente.setId(id_cliente);
        
        stmt.close();
        con.close();
        
        return cliente;
    }
    public Cliente consultarCpf(String cpf) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtConsultarCpf);
        stmt.setString(1, cpf);
        rs = stmt.executeQuery();
        rs.next();
        
        Cliente cliente = new Cliente(
                rs.getString("nome"),
                rs.getString("sobrenome"),
                rs.getString("cpf"));
        
        cliente.setId(rs.getInt("id_cliente"));
        
        stmt.close();
        con.close();
        
        return cliente;
    }
    public List<Cliente> buscarClientes(String termo) throws SQLException
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(stmtBuscarClientes);
        stmt.setString(1, termo);
        rs = stmt.executeQuery();
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        while(rs.next())
        {
            Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("cpf")
            );
            cliente.setId(rs.getInt("id_cliente"));
            listaClientes.add(cliente);
        }
        
        stmt.close();
        con.close();
        
        return listaClientes;
    }
}
