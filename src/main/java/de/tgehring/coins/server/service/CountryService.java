package de.tgehring.coins.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tgehring.coins.server.persistence.model.Country;
import de.tgehring.coins.server.persistence.repository.CountryRepository;

@Service
public class CountryService
{
	@Autowired
	private CountryRepository repository;
	
	@Transactional
	public Country create(Country country)
	{
		return repository.save(country);
	}
	
	@Transactional
	public Country get(Integer id)
	{
		return repository.findOne(id);
	}
	
	@Transactional
	public void delete(Integer id)
	{
		repository.delete(id);
	}
	
	@Transactional
	public Country update(Country country)
	{
		Country existing = repository.findOne(country.getId());
		
		existing.setCode(country.getCode());
		existing.setName(country.getName());
		
		return existing;
	}
	
	@Transactional
	public List<Country> get()
	{
		return repository.findAll();
	}
}
