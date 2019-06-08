package br.edu.ifpb.pweb2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;


@Repository
public class VagaDAO {
	
	private static VagaDAO instance = null;

	@PersistenceContext
	protected EntityManager manager;

	public static VagaDAO getInstance() {
		if (instance == null)
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
//		manager.remove(removed);
		manager.merge(v);
		return v;
	}

	public List<Vaga> findAll() {
		return manager.createQuery("select v from Vaga v", Vaga.class).getResultList();
	}
	
	public Evento findEventoByVaga(Long idvaga) {
		Query query = manager.createQuery("select v.evento from Vaga v where v.id = :idvaga", Evento.class);
		query.setParameter("idvaga", idvaga);
		try {
			Evento e = (Evento) query.getSingleResult();
			return e;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public Vaga delete(Long id) {
		Vaga vaga = findById(id);
		manager.remove(vaga);
		System.out.println("testando vaga ="+vaga);
		return vaga;
	}

	public ArrayList<Vaga> findByEspecialidade(Long especialidadeId) {
		Query q = manager.createQuery("select v from Vaga v where v.especialidade.id = :especialidadeId", Vaga.class);
		q.setParameter("especialidadeId", especialidadeId);
		return (ArrayList<Vaga>) q.getResultList();
	}

}
