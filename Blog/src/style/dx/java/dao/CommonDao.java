package style.dx.java.dao;

import java.util.List;

public interface CommonDao<T, K> {
	List<T> findAll();

	T insert(T t);

	boolean delete(T t);

	default boolean deleteAll() {
		for (T t : findAll()) {
			delete(t);
		}
		return true;
	}

	default int count() {
		return findAll().size();
	}
}
