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
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> requestBody)
    {
        try{
            String email = (String) requestBody.get("email");
            String requestPassword = (String) requestBody.get("password");

            Optional<User> userData =	userRepository.findByEmail(email);
             if(!userData.isPresent())
                 return new ResponseEntity<Object>(new Response("404", "emailid " + email + " not found"), HttpStatus.NOT_FOUND);

            User user = userData.get();

            Boolean isEnabled= user.getEnabled();
            AppUserRole role = user.getAppUserRole();
            String password = user.getPassword();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(requestPassword);
            boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);

            if(!isEnabled)
                return new ResponseEntity<Object>(new Response("401", "User has yet to confirm email"), HttpStatus.UNAUTHORIZED);
            else if(!isPasswordMatch)
                return new ResponseEntity<Object>(new Response("403", "password does not match"), HttpStatus.FORBIDDEN);
            else
                return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        catch (Exception exception){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    	
    }	

    @PostMapping("/user")
    public ResponseEntity<Object> getUser(@RequestBody Map<String, Object> requestBody) {
        try{

            Long userId = ((Number) requestBody.get("userId")).longValue();
            Optional<User> userData = userService.getUser(userId);
            if(!userData.isPresent())
                return new ResponseEntity<Object>(new Response("404","Disease id "+ userId+" does not exists"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }


}
