package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.*;
import edu.sjsu.cmpe275.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    VaccinationRepository vaccinationRepository;

    @Autowired
    UserVaccinationRepository userVaccinationRepository;

    @Autowired
    VaccinationShotRepository vaccinationShotRepository;

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
        try{
            Appointment newAppointment = new Appointment();
            List<Vaccination> vaccinations = new ArrayList<>();

            Optional<User> userData = userRepository.findById(userId);
            Optional<Clinic> clinicData = clinicRepository.findById(clinicId);
            vaccinations = vaccinationRepository.findAllById(vaccinationIds);
            for (Vaccination vaccination :
                    vaccinations) {
                UserVaccination userVaccinationRecord = userVaccinationRepository.findUserVaccinationByVaccinationId(vaccination.getId());

            }

            if (!userData.isPresent()) {
                System.out.println("User not found!");
                return null;
            }
            if (!clinicData.isPresent()) {
                System.out.println("Clinic not found!");
                return null;
            }
            newAppointment.setUser(userData.get());
            newAppointment.setClinic(clinicData.get());
            newAppointment.setAppointmentDateTime(new Timestamp(appointmentDate.getTime()));
            //see how to set time
            newAppointment.setCheckIn(false);


            if (!appointments.isEmpty())
                return appointments;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }
}
