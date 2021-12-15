package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * passenger service to create, delete and get passengers
 */
@Service
public interface  UserService {


    public List<Appointment> getAllUserAppointments(Long id);
    public String singUpUser(User user);


}
