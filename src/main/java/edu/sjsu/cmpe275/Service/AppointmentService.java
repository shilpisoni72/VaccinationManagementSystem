package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.Vaccination;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    public List<Appointment> getAllAppointmentsBetween(String dateStart, String dateEnd);
    public List<Appointment> getAllAppointmentsForUser(Long userId);
    public Appointment bookAppointment(Long userId, String appointmentDate, String appointmentBookedDate, Long clinicId, List<Long> vaccinationIds, int shotNumber);
    public Appointment changeAppointment(Long appointmentId, String appointmentDate, String appointmentBookedDate, List<Long> vaccinationIds, int shotNumber);
    public Integer getShotNumber(Long vaccinationId, Long userId, String appointmentDate);
    public Appointment cancelAppointment(Long appointmentId);
    public Appointment getAppointment(Long appointmentId);


}
