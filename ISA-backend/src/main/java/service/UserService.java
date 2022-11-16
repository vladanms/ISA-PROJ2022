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
	
	public void edit(User user)
	{
		Optional<User> toEditResponse = userRepository.findById(user.getId());
		User toEdit = toEditResponse.get(); 
		
		toEdit.setName(user.getName());
		toEdit.setSurname(user.getSurname());
		toEdit.setAddress(user.getAddress());
		toEdit.setPhone(user.getPhone());
		toEdit.setCity(user.getCity());
		toEdit.setCountry(user.getCountry());
		toEdit.setGender(user.getGender());
		
		userRepository.save(toEdit);
	}
}
