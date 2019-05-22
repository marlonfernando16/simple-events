package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Especialidade;

@Repository
public class EspecialidadeDAO {
	
	private static EspecialidadeDAO instance = null;
	
	@PersistenceContext
	protected EntityManager manager;

	public static EspecialidadeDAO getInstance(){
		if(instance == null)
			instance = new EspecialidadeDAO();
		return instance;
	}
	
	public Especialidade findById(Long id) {
		return manager.find(Especialidade.class, id);
	}

	@Transactional
	public void gravar(Especialidade e) {
		manager.persist(e);
	}
	
	@Transactional
	public Especialidade update(Long id, Especialidade e) {	   
		Especialidade removed = manager.find(Especialidade.class, id);
		manager.remove(removed);
		manager.persist(e);
		return e;
	}

	public List<Especialidade> findAll() {
		return manager.createQuery("select e from Especialidade e", Especialidade.class).getResultList();
	}
	
}
