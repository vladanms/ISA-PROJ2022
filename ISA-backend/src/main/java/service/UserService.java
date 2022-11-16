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
		if(userTemp == null) {
			return false;
		}
		userRepository.save(user);
		return true;
	}
	
	public void remove(Long id) {
		userRepository.deleteById(id);
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
}
