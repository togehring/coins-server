package de.tgehring.coins.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tgehring.coins.server.persistence.model.User;
import de.tgehring.coins.server.persistence.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User	create(User user)
	{
		return repository.save(user);
	}
	
	@Transactional
	public User get(Integer id)
	{
		return repository.findOne(id);
	}
	
	@Transactional
	public void delete(Integer id)
	{
		repository.delete(id);
	}
	
	@Transactional
	public User update(User user)
	{
		User existing = repository.findOne(user.getId());
		
		existing.setUsername(user.getUsername());
		existing.setPassword(user.getPassword());
		
		return existing;
	}
	
	@Transactional
	public List<User> get()
	{
		return repository.findAll();
	}
}
