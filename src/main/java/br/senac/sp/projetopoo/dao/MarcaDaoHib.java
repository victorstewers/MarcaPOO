package br.senac.sp.projetopoo.dao;

import java.util.List;

import br.senac.sp.projetopoo.modelo.Marca;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class MarcaDaoHib implements InterfaceDao<Marca> {
	private EntityManager manager;
	
	public MarcaDaoHib(EntityManager manager) {
		this.manager = manager;
	}
	//finalizar esse projeto fazendo outro cadastro
	@Override
	public void inserir(Marca objeto) throws Exception {
		this.manager.persist(objeto); //Isto faz o INSERT INTO(etc) sem fazer aquelas estruturas do Insert Into do JDBC padrao, quando um objeto já possui um ID ele altera ao inves de inserir
		//HyperNate é uma implementação
		this.manager.persist(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(Marca objeto) throws Exception {
		// TODO Auto-generated method stub
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public Marca buscar(int id) throws Exception {
		this.manager.find(Marca.class, id);
		return this.manager.find(Marca.class, id);
	}

	@Override
	public void excluir(int id) throws Exception {
		Marca marca = buscar(id);
		this.manager.remove(marca);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public List<Marca> listar() throws Exception {
		TypedQuery<Marca> query = this.manager.createQuery("select m from Marca m order by m.nome",Marca.class);
		//caso eu quisesse o id seria ("select m.id from Marca m order by m.nome");
		return query.getResultList();
	}
	
}
