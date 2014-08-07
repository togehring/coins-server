package de.tgehring.coins.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tgehring.coins.server.persistence.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>
{

}
