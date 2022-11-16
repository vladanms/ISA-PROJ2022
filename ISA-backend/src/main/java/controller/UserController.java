package controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import model.User;
import model.UserType;
import service.UserService;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO) {

		User user = new User();
		
		Random rd = new Random();
		user.setId(rd.nextLong());
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
		
		if(userService.register(user)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/editProfile")
	public ResponseEntity<User> editUser(@RequestBody User user)
	{
		
		/*
		toEdit.setName(user.getName());
		toEdit.setSurname(user.getSurname());
		toEdit.setAddress(user.getAddress());
		toEdit.setPhone(user.getPhone());
		toEdit.setCity(user.getCity());
		toEdit.setCountry(user.getCountry());
		toEdit.setGender(user.getGender());
		*/
		if(userService.edit(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
