package revature.controller.endpoint;

import java.util.List;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import revature.controller.dao.UserDAOImpl;
import revature.controller.service.EmailService;
import revature.model.ASNHttpResponse;
import revature.model.User;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins={"http://localhost:4200", "http://127.0.0.1:4200", "http://35.236.194.104:8080", "http://34.125.179.241:9015", }, allowCredentials = "true")

public class UserController {

	private UserDAOImpl userDAO;	
	private EmailService emailService;
	
	@PostMapping(value = "/register")
	@ResponseStatus(value = HttpStatus.OK) // status code 201
	public @ResponseBody ASNHttpResponse addUser(@RequestBody User newUser) {
		newUser.hash();
		///////// This method checks if the username sent by the user is available
		if(userDAO.verifyAccount(newUser)){
			
			userDAO.insertUser(newUser);
			
			return new ASNHttpResponse(200, "Account created successfully");	
			
		}else {	
			
			return new ASNHttpResponse(400, "The account already exist in the system");	
		}	
	}
	
	

	@GetMapping(value = "/all")
	public @ResponseBody List<User> getAllUsers() {
		return userDAO.selectAll();
	}
	
	@GetMapping(value = "/search/{input}")
	public @ResponseBody List<User> getUsersBySearch(@PathVariable("input") String input) {
		return userDAO.selectUsersBySearch(input);
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody User getUserById(@PathVariable("id") int id) {
		return userDAO.selectUserByID(id);
	}	
	
	@PostMapping(value = "/login")
	public User login(HttpSession mySession, @RequestBody User incomingUser) {
		incomingUser.hash();
		User user = userDAO.selectUser(incomingUser);
		if(user != null) {
			mySession.setAttribute("currentUser", user);
			return user;			
		}
		else {
			return new User();									
		}				
	} 
	
	
	@PostMapping(value = "/update")
	@ResponseStatus(value = HttpStatus.OK) // status code 202
	public @ResponseBody User updateUserInfo(HttpSession currentSession, @RequestBody User updateUser) {
		
		updateUser.hash();
		
		
		User tempUser = (User) currentSession.getAttribute("currentUser");
		
		//////// After getting the promp to check for the account
		if(tempUser.getAsnUser().contentEquals(updateUser.getAsnUser())){
			
			userDAO.updateAccount(updateUser);
			System.out.println("Acccount update same Username");
			currentSession.setAttribute("currentUser", updateUser);
			return updateUser;
		
		}else if(userDAO.verifyAccount(updateUser)) {
			
			userDAO.updateAccount(updateUser);
			System.out.println("Acccount update with new Username");
			currentSession.setAttribute("currentUser", updateUser);
			return updateUser;
			
		}
		System.out.println("The username is already taken");
		return tempUser;
	}
	
	
	@GetMapping(value="/session")
	public User getAllUserAccounts(HttpSession mySession){		
		User currentUser = (User) mySession.getAttribute("currentUser");		
		if(currentUser != null)
			return currentUser;
		else
			return new User();		
	}
	
	@PostMapping(value="/logout")
	public ASNHttpResponse logout(HttpSession mySession) {
		mySession.invalidate();		
		return new ASNHttpResponse(200, "You have successfully logged OUT");
	}
	
	@GetMapping(value="/recover/{email}")
	public ASNHttpResponse recoverAccount(@PathVariable("email") String email) {
		System.out.println("email is " + email);
		User account = userDAO.selectUserByEmail(email);
		
		
		///////// This is the information present to the user's email.
		String emailSubject = "Password reset";
		String emailContent = "Forgetting your password is not problem because we got you cover \n\n";
		
		if(account != null) {			
			String newPassword = account.getRandomString();
			account.setAsnUserPassword(newPassword);
			account.hash();
			
			userDAO.updateAccount(account);			
			
			emailService.sendMail(account.getAsnUserEmail(), emailSubject, emailContent + newPassword);
			
			return new ASNHttpResponse(200, "password updated succesfully");
		}			
		else {			
			return new ASNHttpResponse(400, "account not found");			
		}		
	}

	@Autowired
	public UserController(UserDAOImpl userDAO, EmailService emailService) {
		super();
		this.userDAO = userDAO;
		this.emailService = emailService;
	}

	public UserController() { 
		super();
	}
	
	public EmailService getEmailService() {
		return emailService;
	}
	
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}
	
	
}