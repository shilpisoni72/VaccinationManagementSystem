package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Address;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.Clinic;
import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Repository.AppointmentRepository;
import edu.sjsu.cmpe275.Repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public Optional<Clinic> createClinic(){
        System.out.println("inside clinic service impl, create clinic = "  );
        String clinicName = "clinic1";
        Address address = new Address();
        address.setCity("San Jose");
        address.setLine1("fountain plaza");
        address.setState("CA");
        address.setZipCode(95110);

        Clinic clinicObj = new Clinic();
        clinicObj.setClinicName(clinicName);
        clinicObj.setAddress(address);
        try{
            Optional<Clinic> clinic =  Optional.of(clinicRepository.save(clinicObj));
            System.out.println("clinic name and id = "+  clinic.get().getClinicName() + " " + clinic.get().getId());
            return clinic;
        }catch (Exception e){
            System.out.println("exception thrown create disease error  = " +e);
            return null;
        }
    }

    @Override
    public Optional<Clinic> getClinicByName(String clinicName) {
        return clinicRepository.findByClinicName(clinicName);
    }

    @Override
    public boolean deleteClinicById(long id) {
        try{
            clinicRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("exception in deleting clinic" + e);
            return false;
        }
    }

    @Override
    public List<Clinic> getAllAvailableClinics(Object object) {
        Date searchDate = new Date();
        String searchTime = "";
        List<Clinic> availableClinics = new ArrayList<>();
        List<Clinic> clinics =  clinicRepository.findAll();
        for(Clinic c : clinics) {
            List<Appointment> appointments = c.getAppointments();
            int phys_num = c.getNumPhysicians();
            int count = 0;
            for (Appointment a : appointments) {
                Date d = a.getDate();
                Time t = a.getTime();
                if (d.equals(searchDate) && t.equals(searchTime)) {
                    count++;
                }
            }
            if (count < phys_num) {
                availableClinics.add(c);
            }
        }
        return availableClinics;
    }

    @Override
    public List<Clinic> getAvailableClinics(String appointmentTime) {
        String searchTime = "";
        Date d = new Date(appointmentTime);
        Timestamp t =  new Timestamp(d.getTime());
        List<Clinic> availableClinics = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();
        appointments =  appointmentRepository.findAllAppointmentByDateTime(t);

        return availableClinics;
    }

}
