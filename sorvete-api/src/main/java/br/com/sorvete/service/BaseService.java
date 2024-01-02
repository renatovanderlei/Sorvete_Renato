package br.com.sorvete.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<E, ID> {
	
	public E save(E e) throws SQLException; 

	public E update(E e, ID id) throws SQLException;

	public E findById(Long codigo) throws SQLException;

	public void delete(Long codigo) throws SQLException;

	public List<E> findAll() throws SQLException;
}
