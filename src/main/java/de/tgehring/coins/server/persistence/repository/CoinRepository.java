package de.tgehring.coins.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tgehring.coins.server.persistence.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, Integer>
{
	@Query("select c from Coin c where c.country.id = :countryId")
	public List<Coin> findByCountryId(@Param("countryId") Integer countryId);
	
	@Query("select sum(c.value) from Coin c")
	public String sumCoins();
}
