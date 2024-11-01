package br.senac.sp.projetopoo.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import br.senac.sp.projetopoo.modelo.Marca;
public class MarcaTableModel extends AbstractTableModel {
	private List<Marca> lista;
	private String[] cabecalho = {"Id","Nome"};
	
	
	
	public MarcaTableModel(List<Marca> lista) {
		
		this.lista = lista;
	}
	
	

	public void setLista(List<Marca> lista) {
		this.lista = lista;
	}



	@Override
	public int getRowCount() {
		//qmtas linhas
		
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		//qntas colunas
		
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//valor de cada celula puxado
		Marca m = lista.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return m.getId();
		case 1:
			return m.getNome();
		default:
			return null;
		}
		
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecalho[column];
	}
	
}
