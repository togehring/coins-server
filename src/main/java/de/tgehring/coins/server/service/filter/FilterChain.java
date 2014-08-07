package de.tgehring.coins.server.service.filter;

import java.util.ArrayList;
import java.util.List;

import de.tgehring.coins.server.persistence.model.Coin;

public class FilterChain implements FilterInterface
{
	private List<FilterInterface> filters;
	
	public FilterChain()
	{
		this.filters = new ArrayList<>();
	}
	
	public void addFilter(FilterInterface filter)
	{
		filters.add(filter);
	}
	
	@Override
	public List<Coin> filter(List<Coin> base) {
		if (filters.isEmpty()) {
			return base;
		}
		
		for (FilterInterface filter: filters) {
			base = filter.filter(base);
		}
		
		return base;
	}

}
