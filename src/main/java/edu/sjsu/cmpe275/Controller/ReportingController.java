package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.User;
import edu.sjsu.cmpe275.Service.ReportingService;
import edu.sjsu.cmpe275.Service.ReportingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/patientReport")
    public ResponseEntity<List<Appointment>> getPatientReport(@PathVariable ("startDate") Date startDate, @PathVariable ("endDate") Date endDate) {
        System.out.println("request object: start date = " + startDate + " end date = " + endDate);
        try {
            List<Appointment> appointments = new ArrayList<>();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/systemReport")
    public ResponseEntity<List<Appointment>> getSystemReport() {
        System.out.println("inside system report controller");
        try {
            List<Appointment> appointments = new ArrayList<>();
            return new ResponseEntity<>(appointments , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
