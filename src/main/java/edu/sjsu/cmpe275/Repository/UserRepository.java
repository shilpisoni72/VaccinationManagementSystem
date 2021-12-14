package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Override
    List<User> findAll();

//    List<Appointment>

   

}
