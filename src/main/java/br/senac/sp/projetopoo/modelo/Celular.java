package br.senac.sp.projetopoo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Celular {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="modelo",columnDefinition="varchar(50)",nullable = false)
	private String modelo;
	@Column(columnDefinition = "mediumblob")
	private byte[] imgCelular;
	@ManyToOne
	private Marca marca;
	@Column(columnDefinition = "varchar(50)", nullable = false)
	private String sistemaOperacional;
	
	
}
