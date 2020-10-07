package com.springbootstudy.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springbootstudy.course.entities.User;
import com.springbootstudy.course.repositories.UserRepository;
import com.springbootstudy.course.services.exceptions.DatabaseException;
import com.springbootstudy.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));		
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {			
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public User update(Long id, User user) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, user);
			return repository.save(entity);
			
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
				
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
