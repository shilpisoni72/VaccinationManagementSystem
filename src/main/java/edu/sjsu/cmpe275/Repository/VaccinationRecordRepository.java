package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
}