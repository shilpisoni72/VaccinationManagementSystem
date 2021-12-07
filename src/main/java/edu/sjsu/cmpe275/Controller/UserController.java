package edu.sjsu.cmpe275.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sjsu.cmpe275.Helper.Error.Response;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Repository.UserRepository;
import edu.sjsu.cmpe275.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository passengerRepository;

    @Autowired
    UserServiceImpl passengerService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllPassengers() {
        try {
            List<User> passengers = new ArrayList<User>();

            passengerRepository.findAll().forEach(passengers::add);

            if (passengers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
