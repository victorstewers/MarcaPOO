package br.senac.sp.projetopoo.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import br.senac.sp.projetopoo.modelo.Celular;
public class CelularTableModel extends AbstractTableModel{
	private List<Celular> lista;
	private String[] cabecalho = {"Id","Modelo","Marca","Sistema Operacional"};
	
	
	public CelularTableModel(List<Celular> lista) {
		this.lista = lista;
	}
	
	public void setLista(List<Celular> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//valor de cada celula puxada
		Celular c = lista.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getModelo();
		case 2:
			return c.getMarca();
		case 3: 
			return c.getSistemaOperacional();
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecalho[column];
	}
}
