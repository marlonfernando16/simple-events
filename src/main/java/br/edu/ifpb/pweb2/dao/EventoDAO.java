package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Evento;

@Repository
public class EventoDAO {

	private static EventoDAO instance = null;

	@PersistenceContext
	private EntityManager manager;

	public static EventoDAO getInstance() {
		if (instance == null)
			instance = new EventoDAO();
		return instance;
	}

	@Transactional
	public void gravar(Evento evento) {
		manager.persist(evento);
	}

	public List<Evento> findAll() {
		return manager.createQuery("select e from Evento e", Evento.class).getResultList();
	}

	@Transactional
	public Evento update(Long id, Evento evento) {
		Evento removed = manager.find(Evento.class, id);
		manager.remove(removed);
		manager.merge(evento);
		return evento;
	}

	public Evento findById(Long id) {
		return manager.find(Evento.class, id);
	}

	@Transactional
	public Evento delete(Long id) {
		Evento evento = findById(id);
		manager.remove(evento);
		return evento;
	}

}
