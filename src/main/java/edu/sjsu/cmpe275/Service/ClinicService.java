package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    public Optional<Clinic> createClinic();
    public Optional<Clinic> getClinicByName(String clinicName);
    public boolean deleteClinicById(long id);
    public List<Clinic> getAllAvailableClinics(Object object);
}
