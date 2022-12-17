package main.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import main.dto.UserDTO;
import main.model.Center;
import main.model.User;
import main.model.UserRegistered;
import main.repository.UserRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private JavaMailSender mailSender;
	
	public Boolean register(UserRegistered user) throws MessagingException, UnsupportedEncodingException {
		User userTemp = userRepository.findByEmail(user.getEmail());
		if(userTemp != null) {
			return false;
		}
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setEnabled(false);
		userRepository.save(user);
		sendVerificationEmail(user);
		
		return true;
	}
	
	public void sendVerificationEmail(UserRegistered user) throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "ISA-Email";
	    String senderName = "FTN";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "FTN.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getName() + " " + user.getSurname());
	    String verifyURL = "http://localhost:8091/user/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
	
	public boolean verify(String verificationCode) {
	    UserRegistered user = (UserRegistered) userRepository.findByVerificationCode(verificationCode);
	     
	    if (user == null || user.isEnabled()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setEnabled(true);
	        userRepository.save(user);
	         
	        return true;
	    }
	     
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
	
	public ArrayList<User> findAll(){
        ArrayList<User> users = new ArrayList<User>();
        for (User u: userRepository.findAll()) {
        	users.add(u);
        }
        return users;
    }
	
}
