package de.tgehring.coins.server.service.filter;

import java.util.List;

import de.tgehring.coins.server.persistence.model.Coin;

public interface FilterInterface
{
	public List<Coin> filter(List<Coin> base);
}
