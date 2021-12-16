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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaccination")
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
    public ResponseEntity<Object> createVaccination(@RequestBody Map<String, Object> requestBody) {
        try {
            System.out.println("Here");
            String name = (String) requestBody.get("name");
            String manufacturer = (String) requestBody.get("manufacturer");
            Integer numberOfShots = (Integer) requestBody.get("numberOfShots");
            Integer shotInterval = (Integer) requestBody.get("shotInterval");
            Integer duration = (Integer) requestBody.get("duration");
            List<Integer> diseaseIdsOld = (List<Integer>) requestBody.get("diseaseIds");
            List<Long> diseaseIds = new ArrayList<Long>();

            for (Integer id :
                    diseaseIdsOld) {
                Long newId = new Long(id);
                diseaseIds.add(newId);
            }


            return new ResponseEntity<Object>(vaccinationService.createVaccination(name, diseaseIds, manufacturer, numberOfShots, shotInterval, duration), HttpStatus.OK);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/create")
//    @Transactional
//    public ResponseEntity<Object> createVaccination(@RequestBody String name, List<Long> diseaseIds, String manufacturer, Integer numberOfShots, Integer shotInterval, Integer duration) {
//        try {
//            return new ResponseEntity<Object>(vaccinationService.createVaccination(name, diseaseIds, manufacturer, numberOfShots, shotInterval, duration), HttpStatus.OK);
//        } catch (Exception exception) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
