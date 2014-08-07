package de.tgehring.coins.server.service.filter;

import java.util.ArrayList;
import java.util.List;

import de.tgehring.coins.server.persistence.model.Coin;

public class ValueFilter implements FilterInterface
{
	private double value;
	
	public ValueFilter(double value)
	{
		this.value = value;
	}
	
	public List<Coin> filter(List<Coin> base)
	{
		if (base.size() <= 0) {
			return null;
		}

		List<Coin> coins = new ArrayList<Coin>();
		for (Coin coin: base) {
			if (coin.getValue() == this.value) {
				coins.add(coin);
			}
		}

		return coins;
	}
}
