package edu.sjsu.cmpe275.Repository;

import edu.sjsu.cmpe275.Model.UserVaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserVaccinationRepository extends JpaRepository<UserVaccination, Long> {
    public List<UserVaccination> findAllByUserId(Long userId);
}