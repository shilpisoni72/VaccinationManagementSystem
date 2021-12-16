package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Override
    Optional<User> findById(Long userId);

//    List<Appointment>

   

}
