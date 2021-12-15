package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Helper.Model.VaccinationDue;

import java.util.List;

public interface VaccinationRecordService {
    public List<VaccinationDue> getVaccinationsDue(Long userId, String date);
}
