package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.Vaccination;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    public List<Appointment> getAllAppointmentsBetween(String dateStart, String dateEnd);
    public Appointment bookAppointment(Long userId, Date appointmentDate, Long clinicId, List<Long> vaccinationIds);

}
