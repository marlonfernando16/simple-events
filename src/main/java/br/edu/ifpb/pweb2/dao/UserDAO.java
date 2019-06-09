package br.edu.ifpb.pweb2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.model.User;

@Repository
public class UserDAO {

	private static UserDAO instance = null;
	
	@PersistenceContext
	protected EntityManager manager;

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	@Transactional
	public void gravar(User user) {
		manager.persist(user);
	}

	public List<User> findAll() {
		return manager.createQuery("select u from User u", User.class).getResultList();
	}

	public User findByEmail(String email) {
		Query query = manager.createQuery("select u from User u where u.email = :email", User.class);
		query.setParameter("email", email);
		try {
			User u = (User) query.getSingleResult();
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	public User findByName(String nome) {
		return manager.find(User.class, nome);
	}

	public User findById(Long id) {
		return manager.find(User.class, id);
	}

	@Transactional
	public User update(Long id, User user) {
		User atualizado = manager.find(User.class, id);
		atualizado.setNome(user.getNome());
		atualizado.setTelefone(user.getTelefone());
		atualizado.setDatanascimento(user.getDatanascimento());
		atualizado.setEmail(user.getEmail());
		manager.merge(atualizado);
		return atualizado;
	}


	@Transactional
	public User delete(Long id) {
		User user = findById(id);
		manager.remove(user);
		return user;
	}
}
