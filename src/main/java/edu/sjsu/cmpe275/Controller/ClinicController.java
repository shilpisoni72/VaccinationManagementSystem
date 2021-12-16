package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Helper.Error.Response;
import edu.sjsu.cmpe275.Model.Clinic;
import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Repository.ClinicRepository;
import edu.sjsu.cmpe275.Service.ClinicService;
import edu.sjsu.cmpe275.Service.ClinicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clinic")
public class ClinicController {
    @Autowired
    ClinicServiceImpl clinicService;
    @Autowired
    ClinicRepository clinicRepository;

    @PostMapping("/createClinic")
    public ResponseEntity<Object> createClinic(@RequestBody Map<String, Object> requestBody) {

        String clinicName = (String) requestBody.get("clinicName");
        String diseaseDescription = (String) requestBody.get("diseaseDescription");
        System.out.println("create clinic controller called");
        Optional<Clinic> clinicData = clinicService.getClinicByName(clinicName);
        if(clinicData.isPresent()){
            return new ResponseEntity<Object>(new Response("400","Clinic name "+ clinicName+" already exists"), HttpStatus.BAD_REQUEST);
        }

        clinicData =  clinicService.createClinic();
        if(clinicData.isPresent()){
            Clinic _clinic =  clinicData.get();
            return new ResponseEntity<>(_clinic, HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteClinic/{clinicId}")
    public ResponseEntity<Object> deleteClinic(@PathVariable("clinicId") long clinicId) {
        System.out.println("delete clinic controller called");
        Optional<Clinic> clinicData = clinicRepository.findById(clinicId);
        if(!clinicData.isPresent()){
            return new ResponseEntity<Object>(new Response("400","Clinic id "+ clinicId+" doesn not exists"), HttpStatus.BAD_REQUEST);
        }
        boolean isDeleted = clinicService.deleteClinicById(clinicId);
        if(isDeleted){
            return new ResponseEntity<>("clinic id: "+ clinicId + " deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getClinic/{clinicId}")
    public ResponseEntity<Object> getClinic(@PathVariable("clinicId") long clinicId) {
        System.out.println("get clinic controller called");
        Optional<Clinic> clinicData = clinicRepository.findById(clinicId);
        if(!clinicData.isPresent()) {
            return new ResponseEntity<Object>(new Response("400", "Clinic id " + clinicId + " doesn not exists"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clinicData, HttpStatus.OK);
    }

    @GetMapping("/getAvailableClinics")
    public ResponseEntity<List<Clinic>> getAvailableClinics(@RequestParam String appointmentTime) {
        System.out.println("get available clinic controller called");
        List<Clinic> clinics = clinicService.getAvailableClinics(appointmentTime);
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

}
