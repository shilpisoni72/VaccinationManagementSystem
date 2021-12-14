package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportingServiceImpl {
//    @Autowired
//    AppointmentRepository appointmentRepository;

    public List<Appointment> getPatientReport(){
//        Date a  = new Date();
//        Date b = new Date();
        return new ArrayList<Appointment>();
    }
}
