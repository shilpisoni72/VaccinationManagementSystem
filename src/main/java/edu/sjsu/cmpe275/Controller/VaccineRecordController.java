package edu.sjsu.cmpe275.Controller;


import edu.sjsu.cmpe275.Service.VaccinationRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/uservaccination")
public class VaccineRecordController {

    @Autowired
    VaccinationRecordServiceImpl vaccinationRecordService;

    @PostMapping("/due")
    @Transactional
    public ResponseEntity<Object> getVaccinationsDue(@RequestBody Long userId, Date currentDate){
        try{
            return new ResponseEntity<Object>(vaccinationRecordService.getVaccinationsDue(userId, currentDate.toString()), HttpStatus.OK);
        }
        catch (Exception exception){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
