package edu.sjsu.cmpe275.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * this class implements all the functions of passenger service.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Appointment> getAllUserAppointments(Long id) {
        try{
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return user.get().getAppointments();
            }
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public Optional<User> getUser(Long id) {
        try{
            return userRepository.findById(id);
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }
}
