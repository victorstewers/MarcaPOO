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
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(columnDefinition = "TEXT")
	private String informacoes;
	//com o @Transiente um atributo nao vira uma coluna quando implementado pelo HyperNate
	private double preco;
	private int altura;
	private int largura;
	private int profundidade;
	@ManyToOne
	private Marca marca;
	
	
	
}
