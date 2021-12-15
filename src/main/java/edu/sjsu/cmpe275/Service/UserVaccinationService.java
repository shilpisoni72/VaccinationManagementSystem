package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Helper.Model.VaccinationDue;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.UserVaccination;

import java.util.List;

public interface UserVaccinationService {

    public List<Appointment> getAllUserAppointmentsBetween(Long userId, String dateStart, String dateEnd);
    public List<VaccinationDue> getVaccinationsDue(Long userId, String date);
    public List<UserVaccination> getUserVaccinationHistory(Long userId);


}
