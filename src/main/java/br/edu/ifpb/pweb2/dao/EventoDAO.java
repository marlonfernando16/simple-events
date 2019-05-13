package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Evento;

@Repository
public class EventoDAO {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void gravar(Evento evento) {
		manager.persist(evento);
	}

	public List<Evento> findAll() {
		return manager.createQuery("select e from Evento e", Evento.class).getResultList();
	}
}
