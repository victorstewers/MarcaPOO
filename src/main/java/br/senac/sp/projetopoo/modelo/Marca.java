package br.senac.sp.projetopoo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//para mostrar que essa classe vai ser gerenciada pelo hibernate>
//atraves do @Entity, @Id e @GeneratedValue o java entende que a classe vai ser uma tabela.
@Entity
@Data
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nome",columnDefinition = "varchar(100)", nullable = false)
	private String nome;
	@Column(columnDefinition = "mediumblob")
	private byte[] logo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public byte[] getLogo() {
		return logo;
	}
	
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
}
