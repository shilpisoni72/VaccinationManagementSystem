package edu.sjsu.cmpe275.Controller;

import edu.sjsu.cmpe275.Helper.Error.Response;
import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Repository.DiseaseRepository;
import edu.sjsu.cmpe275.Service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

//    @Autowired
//    DiseaseRepository diseaseRepository;
    @Autowired
    DiseaseService diseaseService;

    @PostMapping("/createDisease")
    public ResponseEntity<Object> createDisease(@RequestParam("diseaseName") String diseaseName, @RequestParam("diseaseDescription") String diseaseDescription) {
        System.out.println("create disease controller called");
        Optional<Disease> diseaseData = diseaseService.getDiseaseByName(diseaseName);
        if(diseaseData.isPresent()){
            return new ResponseEntity<Object>(new Response("400","Disease name "+ diseaseName+" already exists"), HttpStatus.BAD_REQUEST);
        }

        diseaseData =  diseaseService.createDisease(diseaseName, diseaseDescription);
        if(diseaseData.isPresent()){
            Disease _disease =  diseaseData.get();
            return new ResponseEntity<>(_disease, HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
