package org.ufpr.sistemapedidos.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.ufpr.sistemapedidos.Produto;

public class modeloTabelaProdutos extends AbstractTableModel {
    
    private String[] colunas = new String[]{"ID", "Descrição"};
    private List<Produto> lista = new ArrayList();
    
    public modeloTabelaProdutos (List<Produto> lista)
    {
        this.lista = lista;
    }
    
    public modeloTabelaProdutos(){}

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto prod = lista.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return prod.getId();
            case 1: return prod.getDescricao();
            default: return null;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }
    @Override
    public void setValueAt(Object value, int row, int col)
    {
        Produto prod = lista.get(row);
        switch(col)
        {
            case 0:
                prod.setId((Integer) value);
                break;
            case 1:
                prod.setDescricao((String) value);
                break;
        }
        this.fireTableCellUpdated(row, col);
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
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
    public Produto getProduto(int linha)
    {
        return lista.get(linha);
    }
}
