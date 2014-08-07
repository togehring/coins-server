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

import de.tgehring.coins.server.persistence.model.User;
import de.tgehring.coins.server.service.UserService;
import de.tgehring.coins.server.web.dto.CreateUserDto;

@Controller
@Transactional
@RequestMapping(value="/users")
public class UserController extends AbstractController
{
	@Autowired
	private UserService service;
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable("id") final Integer id)
	{
		return service.get(id);
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers()
	{
		return service.get();
	}
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody final CreateUserDto dto, HttpServletResponse response)
    {
		User user = new User();
		user.setUsername(dto.username);
		user.setPassword(dto.password);
		
		User created = service.create(user);

        setLocationHeader(response, "/users/{id}", created.getId());

        return created;
    }
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
	public User updateUser(@RequestBody final User user, HttpServletResponse response)
	{
		return service.update(user);
	}
}
