package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportingService {
    public List<Appointment> getPatientReport();
}
