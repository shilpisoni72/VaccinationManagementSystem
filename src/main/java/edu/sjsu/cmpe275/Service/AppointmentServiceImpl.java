package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointmentsBetween(String dateStart, String dateEnd) {
        try{
            List<Appointment> appointments = new ArrayList<>();
            appointmentRepository.findAllByDateBetween(new Date(dateStart), new Date(dateEnd)).forEach(appointments::add);
            if (!appointments.isEmpty())
                return appointments;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public Appointment bookAppointment(Long userId, Date appointmentDate, Long clinicId, List<Long> vaccinationIds) {
        return null;
    }
}
