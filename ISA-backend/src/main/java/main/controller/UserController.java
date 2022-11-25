package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.dto.UserDTO;
import main.model.User;
import main.model.UserRegistered;
import main.model.UserType;
import main.model.UserCategory;
import main.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	User loggedInUser = new User();
	
	/*public User GetLoggedInUser(){
		
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String loggedInEmail = authentication.getName();	
	loggedInUser = userService.findByEmail(loggedInEmail);
	return loggedInUser;
	}*/
	
	
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO) {

		UserRegistered user = new UserRegistered();
		
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setJmbg(userDTO.getJmbg());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		user.setPhone(userDTO.getPhone());
		user.setGender(userDTO.getGender());
		user.setType(UserType.Registered);
		user.setOccupation(userDTO.getOccupation());
		user.setCompany(userDTO.getCompany());
		user.setPoints(0);
		
		if(userService.register(user)) {
			loggedInUser = user;
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody String email, String password)
	{
		if(userService.login(email, password)) {
			loggedInUser = userService.findByEmail(email);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/editProfile")
	public ResponseEntity<User> editUser(@RequestBody UserDTO user)
	{
		User toEdit = userService.findByEmail(user.getEmail());
		if(toEdit == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		toEdit.setName(user.getName());
		toEdit.setSurname(user.getSurname());
		toEdit.setJmbg(user.getJmbg());
		toEdit.setAddress(user.getAddress());
		toEdit.setPhone(user.getPhone());
		toEdit.setCity(user.getCity());
		toEdit.setCountry(user.getCountry());
		toEdit.setGender(user.getGender());
		toEdit.setOccupation(user.getOccupation());
		toEdit.setCompany(user.getCompany());

		if(userService.edit(toEdit)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
