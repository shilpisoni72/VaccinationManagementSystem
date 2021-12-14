package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Helper.Model.VaccinationDue;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.UserVaccination;
import edu.sjsu.cmpe275.Model.Vaccination;
import edu.sjsu.cmpe275.Model.VaccinationShot;
import edu.sjsu.cmpe275.Repository.UserVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserVaccinationServiceImpl implements UserVaccinationService {


    @Autowired
    UserVaccinationRepository userVaccinationRepository;

    @Autowired
    AppointmentServiceImpl appointmentService;

    @Autowired
    UserServiceImpl userService;


    @Override
    public List<Appointment> getAllUserAppointmentsBetween(Long userId, String dateStart, String dateEnd) {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointmentsBetween(dateStart, dateEnd);
            List<Appointment> appointmentsDue = new ArrayList<Appointment>();

            if (appointments.isEmpty() || appointments == null)
                return appointments;

            for (Appointment appointment :
                    appointments) {
                if (appointment.getUser().getId() != userId) {
                    appointmentsDue.add(appointment);
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public List<UserVaccination> getUserVaccinationHistory(Long userId) {
        try {
            List<UserVaccination> userVaccinationHistory = new ArrayList<UserVaccination>();
            userVaccinationRepository.findAllByUserId(userId).forEach(userVaccinationHistory::add);
            if (!userVaccinationHistory.isEmpty())
                return userVaccinationHistory;
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

//    @Override
//    public List<VaccinationDue> getVaccinationsDue(Long userId, String date) {
//        try {
//            List<UserVaccination> userVaccinationHistory = getUserVaccinationHistory(userId);
//            List<VaccinationDue> vaccinationsDue = new ArrayList<>();
//            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date currentDate = new Date(date);
//            Calendar c = Calendar.getInstance();
//            c.setTime(currentDate);
//            c.add(Calendar.MONTH, 12);
//            Date endDate = c.getTime();
//
//            for (UserVaccination userVaccination :
//                    userVaccinationHistory) {
//                //check if the user has taken at least one shot of that vaccine and the vaccine shots are not completed
//                if (userVaccination.getVaccination().getNumberOfShots() != userVaccination.getVaccinationShots().size() && !userVaccination.getVaccinationShots().isEmpty()) {
//
//                    VaccinationDue vaccinationDue = new VaccinationDue();
//                    List<Appointment> userAppointments = getAllUserAppointmentsBetween(userId, date, endDate.toString());
//                    Date lastShotDate = null;
//                    int lastShot = 0;
//                    Calendar c1 = Calendar.getInstance();
//
//                    //get latest shot number and date for that vaccination
//                    for (VaccinationShot vaccinationShot :
//                            userVaccination.getVaccinationShots()) {
//                        if (lastShot < vaccinationShot.getShotNumber()) {
//                            lastShotDate = vaccinationShot.getDate();
//                            lastShot = vaccinationShot.getShotNumber();
//                        }
//                    }
//
//                    c1.setTime(lastShotDate);
//                    c1.add(Calendar.DATE, userVaccination.getVaccination().getShotInterval());
//                    Date nextShotDate = c1.getTime();// get date of the next shot
//
//                    Date earliestAppointmentDate = new Date(endDate.toString());
//                    // get the shot appointment
//                    for (Appointment userAppointment :
//                            userAppointments) {
//                        Date appointmentBookedOn = new Date(userAppointment.getBookedOn().getTime());
//                        if (appointmentBookedOn.before(currentDate)) {
//                            for (Vaccination vaccination :
//                                    userAppointment.getVaccinations()) {
//                                if (userVaccination.getVaccination().getId().equals(vaccination.getId())) {
//                                    if (userAppointment.getDate().before(earliestAppointmentDate) && userAppointment.getDate().after(lastShotDate)) {
//                                        earliestAppointmentDate = userAppointment.getDate();
//                                        vaccinationDue.setAppointment(userAppointment);
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    vaccinationDue.setVaccinatioName(userVaccination.getVaccination().getName());
//                    vaccinationDue.setNumberOfShotDue(lastShot + 1);
//                    vaccinationDue.setDueDate(nextShotDate);
//
//                    if (currentDate.before(nextShotDate) && nextShotDate.before(endDate)) {
//                        vaccinationDue.setStatus("DUE");
//                        vaccinationsDue.add(vaccinationDue);
//                    } else if (currentDate.after(nextShotDate) && nextShotDate.before(endDate)) {
//                        vaccinationDue.setStatus("OVERDUE");
//                        vaccinationsDue.add(vaccinationDue);
//                    }
//                }
//            }
//        } catch (Exception exception) {
//            System.out.println(exception.getStackTrace());
//        }
//        return null;
//
//    }

    @Override
    public List<VaccinationDue> getVaccinationsDue(Long userId, String date) {
        try {
            List<UserVaccination> userVaccinationHistory = getUserVaccinationHistory(userId);
            List<VaccinationDue> vaccinationsDue = new ArrayList<>();
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date currentDate = new Date(date);
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.MONTH, 12);
            Date endDate = c.getTime();

            for (UserVaccination userVaccination :
                    userVaccinationHistory) {
                //check if the user has taken at least one shot of that vaccine and the vaccine shots are not completed
                if (userVaccination.getVaccination().getNumberOfShots() != userVaccination.getVaccinationShots().size() && !userVaccination.getVaccinationShots().isEmpty()) {

                    VaccinationDue vaccinationDue = new VaccinationDue();
                    List<Appointment> userAppointments = getAllUserAppointmentsBetween(userId, date, endDate.toString());
                    Date lastShotDate = null;
                    int lastShot = 0;
                    Calendar c1 = Calendar.getInstance();

                    //get latest shot number and date for that vaccination
                    for (VaccinationShot vaccinationShot :
                            userVaccination.getVaccinationShots()) {
                        if (lastShot < vaccinationShot.getShotNumber()) {
                            lastShotDate = vaccinationShot.getDate();
                            lastShot = vaccinationShot.getShotNumber();
                        }
                    }

                    c1.setTime(lastShotDate);
                    c1.add(Calendar.DATE, userVaccination.getVaccination().getShotInterval());
                    Date nextShotDate = c1.getTime();// get date of the next shot

                    Date earliestAppointmentDate = new Date(endDate.toString());
                    // get the shot appointment
                    for (Appointment userAppointment :
                            userAppointments) {
                        Date appointmentBookedOn = new Date(userAppointment.getBookedOn().getTime());
                        if (appointmentBookedOn.before(currentDate)) {
                            for (Vaccination vaccination :
                                    userAppointment.getVaccinations()) {
                                if (userVaccination.getVaccination().getId().equals(vaccination.getId())) {
                                    if (userAppointment.getDate().before(earliestAppointmentDate) && userAppointment.getDate().after(lastShotDate)) {
                                        earliestAppointmentDate = userAppointment.getDate();
                                        vaccinationDue.setAppointment(userAppointment);
                                    }
                                }
                            }
                        }
                    }

                    vaccinationDue.setVaccinatioName(userVaccination.getVaccination().getName());
                    vaccinationDue.setNumberOfShotDue(lastShot + 1);
                    vaccinationDue.setDueDate(nextShotDate);

                    if (currentDate.before(nextShotDate) && nextShotDate.before(endDate)) {
                        vaccinationDue.setStatus("DUE");
                        vaccinationsDue.add(vaccinationDue);
                    } else if (currentDate.after(nextShotDate) && nextShotDate.before(endDate)) {
                        vaccinationDue.setStatus("OVERDUE");
                        vaccinationsDue.add(vaccinationDue);
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;

    }
}
