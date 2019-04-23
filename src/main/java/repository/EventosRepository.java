package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Evento;
import model.User;

public class EventosRepository {

	private static Map<Long, Evento> data = new HashMap<>();
	
	public static Evento store(Evento e) {
		List<Long> ids = new ArrayList<>(data.keySet());
		Collections.sort(ids);
		Long max = 0L;
		if(ids.size() != 0) {
			max = (Long) ids.get(ids.size()-1);
		}
		data.put(++max, e);
		e.setId(max);
		return e;
	}
	
	public static List<Evento> findAll() {
		return new ArrayList<>(data.values());
	}
	
	public static Evento findById(Long id) {
		return data.get(id);
	}
	
	public static boolean update(Long id, Evento evento) {
		evento.setId(id);
		return data.replace(id, findById(id), evento);
	}
	
	public static Evento delete(Long id) {
		Evento e = findById(id);
		if (e != null) {
			data.remove(e.getId(), e);
			return e;
		}
		return null;
	}
}
