package main.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.dto.UserDTO;
import main.model.User;
import main.model.UserRegistered;
import main.model.UserType;
import main.model.UserCategory;
import main.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	User loggedInUser = new User();
	
	/*
	@PostMapping("/login")
	public ResponseEntity<String> <UserTokenState> createAuthenticationToken(
		@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}*/


	@PostMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody UserDTO userDTO){
		if(userService.login(userDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO) throws MessagingException, UnsupportedEncodingException {

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
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
	    if (userService.verify(code)) {
	        return "verify_success";
	    } else {
	        return "verify_fail";
	    }
	}
	
	@PutMapping("/editProfile")
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
	
	@GetMapping("/getAll")
    public @ResponseBody ArrayList<User> getAll(){ return userService.findAll(); }
}
