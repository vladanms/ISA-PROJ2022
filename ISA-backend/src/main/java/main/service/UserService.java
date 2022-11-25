package main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dto.UserDTO;
import main.model.User;
import main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Boolean register(User user) {
		User userTemp = userRepository.findByEmail(user.getEmail());
		if(userTemp != null) {
			return false;
		}
		userRepository.save(user);
		return true;
	}
	
	public void remove(Long id) {
		userRepository.deleteById(id);
	}
	
	public User findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public Boolean edit(User user)
	{
		User toEditResponse = userRepository.findByEmail(user.getEmail());
		User toEdit = toEditResponse; 
		if(toEdit == null)
		{
			return false;
		}

		//userRepository.save(user);
		return true;
	}
	
	public Boolean login(String email, String password)
	{
		User user = userRepository.findByEmail(email);
		if(user == null)
		{
			return false;
		}
		if(user.getPassword().equals(password))
		{
			return true;
		}
		return false;
	}
	
}
