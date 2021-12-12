package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    DiseaseRepository diseaseRepository;

    @Override
    public Optional<Disease> createDisease(String diseaseName, String description){
        System.out.println("inside disease service impl, create disease = " + diseaseName );
        try{
            Optional<Disease> disease =  Optional.of(diseaseRepository.save(new Disease(diseaseName, description))) ;
            System.out.println("disease name and id = "+  disease.get().getName() + " " + disease.get().getId());
            return disease;
        }catch (Exception e){
            System.out.println("exception thrown create disease error  = " +e);
            return null;
        }
    }

    @Override
    public Optional<Disease> getDiseaseByName(String diseaseName){
        return diseaseRepository.findByDiseaseName(diseaseName);
    }

    @Override
    public boolean deleteDiseaseById(long id){
        try{
            diseaseRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("exception in deleting disease" + e);
            return false;
        }
    }
}
