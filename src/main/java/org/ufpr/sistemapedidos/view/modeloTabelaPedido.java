package org.ufpr.sistemapedidos.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.ufpr.sistemapedidos.ItemDoPedido;

public class modeloTabelaPedido extends AbstractTableModel{
    
    private String[] colunas = new String[]{"Produto", "Quantidade"};
    private List<ItemDoPedido> lista = new ArrayList();
    
    public modeloTabelaPedido (List<ItemDoPedido> lista)
    {
        this.lista = lista;
    }
    
    public modeloTabelaPedido(){}
    

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }
    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemDoPedido item = lista.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return item.getProduto().getDescricao();
            case 1: return item.getQuantidade();
            default:return null;
        }
    }
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    public void setLista(List<ItemDoPedido> lista) {
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
    public ItemDoPedido getItemDoPedido(int linha)
    {
        return lista.get(linha);
    }
    
}
