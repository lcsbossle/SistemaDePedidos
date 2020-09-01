package org.ufpr.sistemapedidos.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.ufpr.sistemapedidos.Cliente;


public class modeloTabelaClientes extends AbstractTableModel
{
    private String[] colunas = new String[]{"ID","Nome", "Sobrenome", "CPF"};
    private List<Cliente> lista = new ArrayList();
    
    public modeloTabelaClientes(List<Cliente> lista)
    {
        this.lista = lista;
    }
    
    public modeloTabelaClientes(){}
    
    @Override
    public int getRowCount()
    {
        return this.lista.size();
    }
    
    @Override
    public int getColumnCount()
    {
        return this.colunas.length;
    }
    
    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Cliente client = lista.get(rowIndex);
        switch (columnIndex)
        {
            case 0: return client.getId();
            case 1: return client.getNome();
            case 2: return client.getSobreNome();
            case 3: return client.getCpf();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col)
    {
        Cliente client = lista.get(row);
        switch(col)
        {
            case 0:
                client.setId((Integer) value);
                break;
            case 1:
                client.setNome((String) value);
                break;
            case 2:
                client.setSobreNome((String) value);
                break;
            case 3:
                client.setCpf((String) value);
                break;
        }
        this.fireTableCellUpdated(row, col);
    }
    
    public boolean removeCliente(Cliente cliente)
    {
        int linha = this.lista.indexOf(cliente);
        boolean result = this.lista.remove(cliente);
        this.fireTableRowsDeleted(linha, linha);
        return result;
    }
    
    public void adicionaCliente(Cliente cliente)
    {
        this.lista.add(cliente);
        this.fireTableRowsInserted(lista.size()-1, lista.size()-1);
    }
    
    public void setListaClientes(List<Cliente> clientes)
    {
        this.lista = clientes;
        this.fireTableDataChanged();
    }
    
    public void limpaTabela()
    {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);
    }
    
    public Cliente getCliente(int linha)
    {
        return lista.get(linha);
    }
}
