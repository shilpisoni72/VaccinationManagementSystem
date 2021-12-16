package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Helper.Model.PatientRecord;
import edu.sjsu.cmpe275.Helper.Model.SystemRecord;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportingServiceImpl {
    @Autowired
    AppointmentRepository appointmentRepository;
    public PatientRecord getPatientReport(String userId, String startDate, String endDate, String currDate){

        System.out.println("inside get patient report ");
        Long id = Long.parseLong(userId);
        Date sDate = new Date(startDate);
        Date eDate = new Date(endDate);
        Date cDate = new Date(currDate);
        System.out.println("in patient report serviveImpls = id = " + id + " sDate = " + sDate + " eDate = " + eDate);
        List<Appointment> allAppointments = appointmentRepository.findAllByUserIdAndDateBetween(id, sDate, eDate);
        List<Appointment> totalAppointments =new ArrayList<>();
        List<Appointment> noShowAppointments = new ArrayList<>();
        PatientRecord patientRecord = new PatientRecord();
        if(allAppointments.size() == 0){
            patientRecord.noShowAppointments = new ArrayList<>();
            patientRecord.totalAppointments = new ArrayList<>();
            patientRecord.noShowRate = 0;
            return patientRecord;
        }
        int noShow =0;
        for(int i = 0; i < allAppointments.size(); i++){
            Appointment a = allAppointments.get(i);
            if(a.getCheckIn() == true && a.getDate().before(cDate)){
                totalAppointments.add(a);
            }else{
                noShowAppointments.add(a);
                noShow++;
            }
        }
        double noShowRate = noShow/allAppointments.size();
        System.out.println("all appointments  = " + allAppointments);
        patientRecord.totalAppointments = totalAppointments;
        patientRecord.noShowAppointments = noShowAppointments;
        patientRecord.noShowRate = noShowRate;
        return patientRecord;
    }

    public SystemRecord getSystemReport(String clinicId, String startDate, String endDate){

        System.out.println("inside get system report ");
        Long id = Long.parseLong(clinicId);
        Date sDate = new Date(startDate);
        Date eDate = new Date(endDate);
        System.out.println("in patient report serviveImpls = id = " + id + " sDate = " + sDate + " eDate = " + eDate);
        List<Appointment> allAppointments = appointmentRepository.findAllByUserIdAndDateBetween(id, sDate, eDate);
        System.out.println("all appointments  = " + allAppointments);
        SystemRecord systemRecord = new SystemRecord();
        return systemRecord;
    }
}
