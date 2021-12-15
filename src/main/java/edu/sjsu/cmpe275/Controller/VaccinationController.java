package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Model.Appointment;
import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Service.VaccinationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class VaccinationController {

    @Autowired
    VaccinationServiceImpl vaccinationService;

    @GetMapping("/all")
    @Transactional
    public ResponseEntity<Object> getAllVaccinations() {
        try {
            return new ResponseEntity<Object>(vaccinationService.getAllVaccinations(), HttpStatus.OK);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Object> createVaccination(String name, List<Disease> diseases, String manufacturer, Integer numberOfShots, Integer shotInterval, Integer duration) {
        try {
            return new ResponseEntity<Object>(vaccinationService.createVaccination(name, diseases, manufacturer, numberOfShots, shotInterval, duration), HttpStatus.OK);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
