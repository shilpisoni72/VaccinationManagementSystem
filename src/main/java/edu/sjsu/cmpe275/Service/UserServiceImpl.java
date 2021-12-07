package edu.sjsu.cmpe275.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * this class implements all the functions of passenger service.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository passengerRepository;

 

    /**
     * if there is passenger with same phone number, return error else create new passenger and return new passenger
     * @param fn - first name
     * @param ln - last name
     * @param age - age
     * @param gen - gender
     * @param ph - phone
     * @return Passenger object
     */
    @Override
    @Transactional
    public User createPassengerService(String fn, String ln, int age, String gen, String ph) {

        return null;
    }

    /**
     *
     * @param id - passenger id
     * @return Passenger object
     */
    @Override
    public Optional<User> getPassengerService(long id) {
        return  passengerRepository.findById(id);
    }

    /**
     * get the passenger, check if passenger has any flights, if yes delete all flights and delete passenger at the end.
     * @param id - passenger id
     * @return true if the passenger exits, else false
     */
    @Override
    @Transactional
    public boolean deletePassengerService(long id) {
        //check and delete reservations of passenger
        User passenger = passengerRepository.getById(id);
        System.out.println("passenger  = " + passenger.getFirstName());
        passengerRepository.save(passenger);
        passengerRepository.deleteById(id);
        return true;

    }

}
