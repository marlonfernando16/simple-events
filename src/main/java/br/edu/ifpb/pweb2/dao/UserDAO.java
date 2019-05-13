package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.User;

@Repository
public class UserDAO {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void gravar(User user) {
		manager.persist(user);
	}

	public List<User> findAll() {
		return manager.createQuery("select a from Aluno a", User.class).getResultList();
	}
	
}
