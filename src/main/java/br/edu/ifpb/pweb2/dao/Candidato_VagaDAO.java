package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.model.Candidato_Vaga;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;

@Component("Candidato_VagaDAO")
public class Candidato_VagaDAO {

	private static Candidato_VagaDAO instance = null;

	@PersistenceContext
	protected EntityManager manager;

	public static Candidato_VagaDAO getInstance() {
		if (instance == null)
			instance = new Candidato_VagaDAO();
		return instance;
	}

	public Candidato_Vaga findById(Long id) {
		return manager.find(Candidato_Vaga.class, id);
	}

	@Transactional
	public void gravar(Candidato_Vaga cv) {
		manager.persist(cv);
	}

	@Transactional
	public Candidato_Vaga update(Long id, Candidato_Vaga cv) {
		Vaga removed = manager.find(Vaga.class, id);
		manager.remove(removed);
		manager.merge(cv);
		return cv;
	}

	public List<Candidato_Vaga> findAll() {
		return manager.createQuery("select cv from Candidato_Vaga cv", Candidato_Vaga.class).getResultList();
	}

	public List<Candidato_Vaga> findByUser(User user) {
		Query q = manager.createQuery("select cv from Candidato_Vaga cv where cv.candidato = :user",
				Candidato_Vaga.class);
		q.setParameter("user", user);
		return q.getResultList();
	}

}
