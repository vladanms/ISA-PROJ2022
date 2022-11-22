package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;

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
		Optional<User> toEditResponse = userRepository.findById(user.getId());
		User toEdit = toEditResponse.get(); 
		if(toEdit == null)
		{
			return false;
		}

		userRepository.save(user);
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
