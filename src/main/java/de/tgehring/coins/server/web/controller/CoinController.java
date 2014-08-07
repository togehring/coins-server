package de.tgehring.coins.server.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.tgehring.coins.server.persistence.model.Coin;
import de.tgehring.coins.server.persistence.repository.CountryRepository;
import de.tgehring.coins.server.service.CoinService;
import de.tgehring.coins.server.service.filter.FilterChain;
import de.tgehring.coins.server.service.filter.ValueFilter;
import de.tgehring.coins.server.service.filter.YearFilter;
import de.tgehring.coins.server.web.dto.CreateCoinDto;

@Controller
@Transactional
@RequestMapping(value="/coins")
public class CoinController extends AbstractController
{
	@Autowired
	private CoinService service;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Coin getCoin(@PathVariable("id") final Integer id)
	{
		return service.get(id);
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/sum", method=RequestMethod.GET)
    public String sumCoins()
	{
		return service.sumCoins();
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Coin> getCoins(
    		@RequestParam("countryId") String countryId,
    		@RequestParam(value = "value", required = false) String value,
    		@RequestParam(value = "year", required = false) String year
	)
	{
		FilterChain filterChain = new FilterChain();
		if (value != null) {
			filterChain.addFilter(new ValueFilter(Double.parseDouble(value)));
		}
		
		if (year != null) {
			filterChain.addFilter(new YearFilter(Integer.parseInt(year)));
		}
		
		return filterChain.filter(service.getByCountryId(Integer.parseInt(countryId)));
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Coin createCoin(@RequestBody final CreateCoinDto dto, HttpServletResponse response)
    {
		Coin coin = new Coin();
		
		coin.setCountry(countryRepository.findOne(dto.countryId));
		coin.setValue(dto.value);
		coin.setYear(dto.year);
		coin.setLetter(dto.letter);
		coin.setCommemorative(dto.commemorative);
		coin.setDescription(dto.description);
		
		Coin created = service.create(coin);

        setLocationHeader(response, "/coins/{id}", created.getId());

        return created;
    }
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
	public Coin updateCoin(@RequestBody final Coin coin, HttpServletResponse response)
	{
		return service.update(coin);
	}
}
