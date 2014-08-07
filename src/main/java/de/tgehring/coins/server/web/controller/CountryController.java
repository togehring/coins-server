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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.tgehring.coins.server.persistence.model.Country;
import de.tgehring.coins.server.service.CountryService;
import de.tgehring.coins.server.web.dto.CreateCountryDto;

@Controller
@Transactional
@RequestMapping(value="/countries")
public class CountryController extends AbstractController
{
	@Autowired
	private CountryService service;
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Country getCountry(@PathVariable("id") final Integer id)
	{
		return service.get(id);
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getCountrys()
	{
		return service.get();
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Country createCountry(@RequestBody final CreateCountryDto dto, HttpServletResponse response)
    {
		System.out.println("create");
		Country country = new Country();
		country.setCode(dto.code);
		country.setName(dto.name);
		
		Country created = service.create(country);

        setLocationHeader(response, "/countriess/{id}", created.getId());

        return created;
    }
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
	public Country updateCountry(@RequestBody final Country country, HttpServletResponse response)
	{
		return service.update(country);
	}
}
