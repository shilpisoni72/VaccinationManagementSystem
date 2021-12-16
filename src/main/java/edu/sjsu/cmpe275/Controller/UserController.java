package edu.sjsu.cmpe275.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sjsu.cmpe275.Helper.Error.Response;
import edu.sjsu.cmpe275.Model.AppUserRole;
import edu.sjsu.cmpe275.Model.Clinic;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Repository.UserRepository;
import edu.sjsu.cmpe275.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();



            userRepository.findAll().forEach(users::add);


            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    
    @GetMapping("/login")
    public User login(String email, String pswd)
    {
    	User user =	userRepository.findByEmail(email).orElseThrow(()->new IllegalStateException("User not found !"));
    	Boolean isEnabled= user.getEnabled();
    	AppUserRole role = user.getAppUserRole();
    	
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String password = user.getPassword();
    	String encodedPassword = passwordEncoder.encode(pswd);
    	boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
    	if(isEnabled && isPasswordMatch)
    		return user; // user is valid
    	else
    		return null; //user is invalid
    	
    }	

    @PostMapping("/user")
    public User getUser(@RequestBody Map<String, Object> requestBody) {
        try{

            Long userId = ((Number) requestBody.get("userId")).longValue();
            return userService.getUser(userId);
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }


}
