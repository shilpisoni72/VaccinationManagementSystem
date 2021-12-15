package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Disease;
import edu.sjsu.cmpe275.Model.Vaccination;

import java.util.List;

public interface VaccinationService {
    public List<Vaccination> getAllVaccinations();
    public Vaccination createVaccination(String name, List<Disease> diseases, String manufacturer, Integer numberOfShots, Integer shotInterval, Integer duration);
}
