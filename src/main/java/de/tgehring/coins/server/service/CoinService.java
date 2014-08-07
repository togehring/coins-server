package de.tgehring.coins.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tgehring.coins.server.persistence.model.Coin;
import de.tgehring.coins.server.persistence.repository.CoinRepository;

@Service
public class CoinService
{
	@Autowired
	private CoinRepository repository;
	
	@Transactional
	public Coin create(Coin coin)
	{
		return repository.save(coin);
	}
	
	@Transactional
	public Coin get(Integer id)
	{
		return repository.findOne(id);
	}
	
	@Transactional
	public void delete(Integer id)
	{
		repository.delete(id);
	}
	
	@Transactional
	public Coin update(Coin coin)
	{
		Coin existing = repository.findOne(coin.getId());
		
		existing.setCountry(coin.getCountry());
		existing.setValue(coin.getValue());
		existing.setYear(coin.getYear());
		existing.setLetter(coin.getLetter());
		existing.setCommemorative(coin.isCommemorative());
		existing.setDescription(coin.getDescription());
		
		return existing;
	}
	
	@Transactional
	public List<Coin> get()
	{
		return repository.findAll();
	}
	
	@Transactional
	public List<Coin> getByCountryId(int countryId)
	{
		return repository.findByCountryId(countryId);
	}
	
	@Transactional
	public String sumCoins()
	{
		return repository.sumCoins();
	}
}