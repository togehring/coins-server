package de.tgehring.coins.server.service.filter;

import java.util.ArrayList;
import java.util.List;

import de.tgehring.coins.server.persistence.model.Coin;

public class YearFilter implements FilterInterface
{
	private int year;
	
	public YearFilter(int year)
	{
		this.year = year;
	}
	
	@Override
	public List<Coin> filter(List<Coin> base)
	{
		if (base.size() <= 0) {
			return null;
		}

		List<Coin> coins = new ArrayList<Coin>();
		for (Coin coin: base) {
			if (coin.getYear() == this.year) {
				coins.add(coin);
			}
		}

		return coins;
	}

}
