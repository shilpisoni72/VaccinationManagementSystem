package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Helper.Model.PatientRecord;
import edu.sjsu.cmpe275.Helper.Model.SystemRecord;
import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Service.ReportingService;
import edu.sjsu.cmpe275.Service.ReportingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportingController {
    @Autowired
    ReportingServiceImpl reportingService;

    @GetMapping("/patientreports")
    public ResponseEntity<PatientRecord> getPatientReport(@RequestParam String userId, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String currDate) {
        System.out.println("request object: start date = " + startDate + " end date = " + endDate);
        try {
            List<Appointment> appointments = new ArrayList<>();
            PatientRecord patientRecord =  reportingService.getPatientReport(userId, startDate, endDate, currDate);
            return new ResponseEntity<>(patientRecord, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/systemreports")
    public ResponseEntity<SystemRecord> getSystemReport(@RequestParam String clinicId, @RequestParam String startDate, @RequestParam String endDate) {
        System.out.println("inside system report controller");
        try {
            List<Appointment> appointments = new ArrayList<>();
            SystemRecord systemRecord = reportingService.getSystemReport(clinicId, startDate, endDate);
            return new ResponseEntity<>(systemRecord , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
