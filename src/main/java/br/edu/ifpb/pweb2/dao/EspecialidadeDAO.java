package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.Especialidade;

@Repository
public class EspecialidadeDAO {

	private static EspecialidadeDAO instance = null;

	@PersistenceContext
	protected EntityManager manager;

	public static EspecialidadeDAO getInstance() {
		if (instance == null)
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
		Especialidade atualizada = manager.find(Especialidade.class, id);
		atualizada.setDescricao(e.getDescricao());
		atualizada.setNome(e.getNome());
		manager.merge(atualizada);
		return atualizada;
	}

	public List<Especialidade> findAll() {
		return manager.createQuery("select e from Especialidade e", Especialidade.class).getResultList();
	}
	
	public List<Especialidade> findEspecialidadesFilter(List<Especialidade> especialidades) {
		Query query = manager.createQuery("select e from Especialidade e  WHERE e NOT IN :especialidades", Especialidade.class);
		query.setParameter("especialidades", especialidades);
		try {
			List<Especialidade> especialidadesFiltered = (List<Especialidade>)query.getResultList();
			return especialidadesFiltered;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public void delete(Long especialidadeId) {
		Especialidade e = findById(especialidadeId);
		manager.remove(e);
	}

}
