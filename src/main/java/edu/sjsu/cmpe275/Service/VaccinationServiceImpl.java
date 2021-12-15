package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Model.Vaccination;
import edu.sjsu.cmpe275.Model.VaccinationRecord;
import edu.sjsu.cmpe275.Repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationServiceImpl implements VaccinationService {

    @Autowired
    VaccinationRepository vaccinationRepository;

    @Override
    public List<Vaccination> getAllVaccinations() {
        try{
            List<Vaccination> vaccinations = new ArrayList<>();
            vaccinationRepository.findAll().forEach(vaccinations::add);
            if (!vaccinations.isEmpty())
                return vaccinations;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public Vaccination createVaccination(String name, List<Disease> diseases, String manufacturer, Integer numberOfShots, Integer shotInterval, Integer duration) {
        try{
            Vaccination vaccination = new Vaccination();
            vaccination.setName(name);
            vaccination.setManufacturer(manufacturer);
            vaccination.setDuration(duration);
            vaccination.setNumberOfShots(numberOfShots);
            vaccination.setShotInterval(shotInterval);
            vaccination.setDiseases(diseases);
            return vaccinationRepository.save(vaccination);
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }
}
