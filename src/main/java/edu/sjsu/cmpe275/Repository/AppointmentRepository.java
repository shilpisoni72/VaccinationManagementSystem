package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDateBefore(Date date);
    List<Appointment> findAllByDateAfter(Date date);
    List<Appointment> findAllByDateBetween(Date dateStart, Date dateEnd);
//    List<Appointment> findAllByUserAndDateBetween(Date dateStart, Date dateEnd)
    List<Appointment> findAllByUserIdAndDateBetween(Long id, Date dateStart, Date dateEnd);
}