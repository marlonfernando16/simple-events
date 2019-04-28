package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;

public class UserRepository {
	private static Map<Long, User> data = new HashMap<Long, User>();

	public static User store(User user) {
		List<Long> ids = new ArrayList<Long>(data.keySet());

		Collections.sort(ids);
		Long max = (long) 0;
		if (ids.size() != 0) {
			max = (Long) ids.get(ids.size() - 1);
		}
		data.put(++max, user);
		user.setId(max);
		return user;
	}

	public static boolean update(Long id, User user) {
		user.setId(id);
		return data.replace(id, findById(id), user);
	}

	public static User findByName(String nome) {
		for (Map.Entry<Long, User> entry : data.entrySet()) {
			if (entry.getValue().getNome().equals(nome))
				return entry.getValue();
		}
		return null;
	}
	
	public static User findByEmail(String email) {
		for (Map.Entry<Long, User> entry : data.entrySet()) {
			if (entry.getValue().getEmail().equals(email))
				return entry.getValue();
		}
		return null;
	}

	public static User findById(Long id) {
		return data.get(id);
	}

	public static User delete(Long id) {
		User u = findById(id);
		if (u != null) {
			data.remove(u.getId(), u);
			return u;
		}
		return null;
	}

	public static List<User> findAll() {
		return new ArrayList<User>(data.values());
	}
}
