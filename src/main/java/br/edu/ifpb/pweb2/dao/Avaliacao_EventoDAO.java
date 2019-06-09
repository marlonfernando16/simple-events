package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.model.Avaliacao_Evento;
import br.edu.ifpb.pweb2.model.Candidato_Vaga;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;
@Component("Avaliacao_EventoDAO")
public class Avaliacao_EventoDAO {
	private static Avaliacao_EventoDAO instance = null;

	@PersistenceContext
	protected EntityManager manager;

	public static Avaliacao_EventoDAO getInstance() {
		if (instance == null)
			instance = new Avaliacao_EventoDAO();
		return instance;
	}

	public Avaliacao_Evento findById(Long id) {
		return manager.find(Avaliacao_Evento.class, id);
	}

	@Transactional
	public void gravar(Avaliacao_Evento av) {
		manager.persist(av);
	}

	@Transactional
	public Avaliacao_Evento update(Long id, Avaliacao_Evento av) {
		Avaliacao_Evento removed = manager.find(Avaliacao_Evento.class, id);
		manager.merge(av);
		return av;
	}

	public List<Avaliacao_Evento> findAll() {
		return manager.createQuery("select av from Avaliacao_Evento av", Avaliacao_Evento.class).getResultList();
	}
	
	public Avaliacao_Evento findByUserAndEvento(User user, Evento evento) {
		Query q = manager.createQuery("select av from Avaliacao_Evento av where av.participante = :user and av.evento = :evento",
				Avaliacao_Evento.class);
		q.setParameter("user", user);
		q.setParameter("evento", evento);
		try {
			return (Avaliacao_Evento) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

	
	@Transactional
	public Avaliacao_Evento delete(Long id) {
		Avaliacao_Evento av = findById(id);
		manager.remove(av);
		return av;
	}

}
