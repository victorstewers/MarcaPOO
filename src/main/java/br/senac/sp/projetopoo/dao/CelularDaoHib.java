package br.senac.sp.projetopoo.dao;

import java.util.List;

import br.senac.sp.projetopoo.modelo.Celular;
import br.senac.sp.projetopoo.modelo.Marca;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public class CelularDaoHib implements InterfaceDao<Celular> {
	private EntityManager manager;
	
	public CelularDaoHib(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void inserir(Celular objeto) throws Exception {
		
		this.manager.persist(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(Celular objeto) throws Exception {
		// TODO Auto-generated method stub
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public Celular buscar(int id) throws Exception {
		this.manager.find(Celular.class, id);
		return this.manager.find(Celular.class, id);
	}

	@Override
	public void excluir(int id) throws Exception {
		Celular celular = buscar(id);
		this.manager.remove(celular);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
		
	}

	@Override
	public List<Celular> listar() throws Exception {
		TypedQuery<Celular> query = this.manager.createQuery("select c from Celular c order by c.nome",Celular.class);
		//caso eu quisesse o id seria ("select m.id from Marca m order by m.nome");
		return query.getResultList();
	}

}
