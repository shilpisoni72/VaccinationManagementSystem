package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Repository.AppointmentRepository;
import edu.sjsu.cmpe275.Repository.UserRepository;
import edu.sjsu.cmpe275.Repository.UserVaccinationRepository;
import edu.sjsu.cmpe275.Service.AppointmentServiceImpl;
import edu.sjsu.cmpe275.Service.UserVaccinationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/uservaccination")
public class UserVaccinationController {

    @Autowired
    UserVaccinationRepository userVaccinationRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentServiceImpl appointmentService;

    @Autowired
    UserVaccinationServiceImpl userVaccinationService;



    @GetMapping("/due")
    @Transactional
    public ResponseEntity<Object> getVaccinationsDue(@RequestBody Long userId, Date currentDate){
        try{
            return new ResponseEntity<Object>(userVaccinationService.getVaccinationsDue(userId, currentDate.toString()), HttpStatus.OK);
        }
        catch (Exception exception){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
