package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.VaccinationShot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationShotRepository extends JpaRepository<VaccinationShot, Long> {
}