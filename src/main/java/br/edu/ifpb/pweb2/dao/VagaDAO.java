package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Vaga;
@Repository
public class VagaDAO {
private static VagaDAO instance = null;
	
	@PersistenceContext
	protected EntityManager manager;

	public static VagaDAO getInstance(){
		if(instance == null)
			instance = new VagaDAO();
		return instance;
	}
	
	public Vaga findById(Long id) {
		return manager.find(Vaga.class, id);
	}

	@Transactional
	public void gravar(Vaga v) {
		manager.persist(v);
	}
	
	@Transactional
	public Vaga update(Long id, Vaga v) {	   
		Vaga removed = manager.find(Vaga.class, id);
		manager.remove(removed);
		manager.persist(v);
		return v;
	}

	public List<Vaga> findAll() {
		return manager.createQuery("select v from Vaga v", Vaga.class).getResultList();
	}
	

}
