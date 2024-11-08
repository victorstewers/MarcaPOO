package br.senac.sp.projetopoo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFactory {
	private static final EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("senac");
	private static EntityManager manager;
	
	public static EntityManager getEntityManager() {
		if(manager == null) {
			manager = factory.createEntityManager();
		
		}
		return manager;
		// a hora que esse método é chamado ele gera o entitymanager e faz a conexao e mapeamento e cria as tabelas necessarias, na primeira chamada!
		//até agora criamos um DAOdeMarca e utilizamos o modelo relacional e usamos o JDBC padrao
		//vamos criar uma Interface para colocar nos daos, exemplo MarcaDAO, ProdutoDAO, a interface vai ter os métodos void inserir(Produto ou Marca? Para resolver usamos generic)
		//vamos criar a interface como generic InterfaceDao<T>
	}
}
