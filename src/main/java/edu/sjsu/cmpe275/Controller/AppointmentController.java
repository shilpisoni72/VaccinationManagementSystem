package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.VaccinationRecord;
import edu.sjsu.cmpe275.Repository.AppointmentRepository;
import edu.sjsu.cmpe275.Repository.UserVaccinationRepository;
import edu.sjsu.cmpe275.Service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    UserVaccinationRepository userVaccinationRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentServiceImpl appointmentService;

    @PostMapping("/user")
    @Transactional
    public ResponseEntity<Object> getAllAppointmentsForUser(@RequestBody Long userId) {
        try {
            List<Appointment> userAppointments = appointmentService.getAllAppointmentsForUser(userId);
            if (userAppointments.isEmpty())
                return new ResponseEntity<Object>(userAppointments, HttpStatus.NOT_FOUND);
            else if (userAppointments == null)
                return new ResponseEntity<Object>(userAppointments, HttpStatus.INTERNAL_SERVER_ERROR);
            else
                return new ResponseEntity<Object>(userAppointments, HttpStatus.OK);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book")
    @Transactional
    public ResponseEntity<Object> bookAppointment(@RequestBody Long userId, String appointmentDate, String appointmentBookedDate, Long clinicId, List<Long> vaccinationIds, int shotNumber) {
        try {
            Appointment bookedAppointment = appointmentService.bookAppointment(userId, appointmentDate, appointmentBookedDate, clinicId, vaccinationIds, shotNumber);
            if (bookedAppointment != null)
                return new ResponseEntity<Object>(bookedAppointment, HttpStatus.OK);
            else
                return new ResponseEntity<Object>(bookedAppointment, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/shot_number")
    @Transactional
    public ResponseEntity<Object> getShotNumber(@RequestBody Long vaccinationId, Long userId, String date) {
        try {
            Integer shotNumber = appointmentService.getShotNumber(vaccinationId, userId, date);
            if (shotNumber !=-1)
                return new ResponseEntity<Object>(shotNumber, HttpStatus.OK);
            else
                return new ResponseEntity<Object>(shotNumber, HttpStatus.FORBIDDEN);

        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancel")
    @Transactional
    public ResponseEntity<Object> cancelAppointment(@RequestBody Long appointmentId) {
        try {
            Appointment cancelledAppointment = appointmentService.cancelAppointment(appointmentId);
            if (cancelledAppointment !=null)
                return new ResponseEntity<Object>(cancelledAppointment, HttpStatus.OK);
            else
                return new ResponseEntity<Object>("Some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get_one")
    @Transactional
    public ResponseEntity<Object> getAppointment(@RequestBody Long appointmentId) {
        try {
            Appointment appointment = appointmentService.cancelAppointment(appointmentId);
            if (appointment !=null)
                return new ResponseEntity<Object>(appointment, HttpStatus.OK);
            else
                return new ResponseEntity<Object>("Appointment not found", HttpStatus.NOT_FOUND);

        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/checkIn")
    @Transactional
    public ResponseEntity<Boolean> checkInAppointment(@RequestBody Long appointmentId) {
        try {
            boolean isCheckedIn = appointmentService.checkInAppointment(appointmentId);
            if (isCheckedIn == true)
                return new ResponseEntity<Boolean>(isCheckedIn, HttpStatus.OK);
            else
                return new ResponseEntity<Boolean>(isCheckedIn, HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
