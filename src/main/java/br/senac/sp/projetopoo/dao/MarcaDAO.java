package br.senac.sp.projetopoo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.sp.projetopoo.modelo.Marca;

public class MarcaDAO implements InterfaceDao<Marca> {
	private Connection conexao;
	private String sql; 
	private PreparedStatement stmt;
	private int qntidadeMarcas = 0;
	
	public MarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	public void inserir(Marca marca) throws SQLException {

		sql = "INSERT INTO MARCA(NOME, LOGO) VALUES (?,?)";
		stmt  = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.execute();
		stmt.close();
		//caso a conexao abrisse cada vez em que tivesse algum comando teria que fechar cada vez conexao.close();
	}
	
	public List<Marca> listar() throws SQLException{
			List<Marca> lista = new ArrayList<Marca>();
			sql = "select * from marca ";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				qntidadeMarcas++;
				Marca m = new Marca();
				m.setId(rs.getInt("id"));
				m.setNome(rs.getString("nome"));
				m.setLogo(rs.getBytes("logo"));
				lista.add(m);
				
			}
			rs.close();
			stmt.close();
			return lista;
		}
		
	public void alterar(Marca marca) throws SQLException {
		sql = "update marca set nome = ?, logo = ? where id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.setInt(3, marca.getId());
		stmt.execute();
		stmt.close();
		
	
	}
	//Pegar vetor de bytes na marca e construir um imageIcon a partir dele(convert byte[] para ImageIcon). Setar no JLabel
	//Aparecer o icone de cada registro na tela FrameMarca
	public void excluir(int id) throws SQLException {
		sql = "delete from marca where id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		
	}
	
	public String[] vetorMarcas() throws SQLException{
		listar();
		String[] vetorNomeMarcas = new String[qntidadeMarcas];
		sql = "select nome from marca ";
		stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int contadorVetor = 0;
		while(rs.next()) {
			vetorNomeMarcas[contadorVetor] = rs.getString("nome");
			contadorVetor++;
			
			
		}
		rs.close();
		stmt.close();
		return vetorNomeMarcas;
	}
	
	
	@Override
	public Marca buscar(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	}

