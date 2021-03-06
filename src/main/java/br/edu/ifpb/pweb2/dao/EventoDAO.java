package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.Vaga;

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
	
	public List<Especialidade> findEspecialidadesByEvento(Long idevento) {
		Query query = manager.createQuery("select e from Especialidade e LEFT JOIN e.vagas v  LEFT JOIN v.evento ev  where ev.id = :idevento", Especialidade.class);
		query.setParameter("idevento", idevento);
		try {
			List<Especialidade> especialidades = (List<Especialidade>)query.getResultList();
			return especialidades;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public Evento update(Long id, Evento evento) {
		Evento atualizado = manager.find(Evento.class, id);
		atualizado.setDescricao(evento.getDescricao());
		atualizado.setData(evento.getData());
		atualizado.setLocal(evento.getLocal());
		atualizado.setFinalizado(evento.isFinalizado());
		manager.merge(atualizado);
		return atualizado;
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
