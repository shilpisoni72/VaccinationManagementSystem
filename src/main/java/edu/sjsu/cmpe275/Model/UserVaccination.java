package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "user_vaccination")
public class UserVaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_vaccination_id")
    private List<VaccinationShot> vaccinationShots;

    @Column(name = "last_shot_date")
    private Timestamp lastShotDate;

    @Column(name = "last_shot_number")
    private Integer lastShotNumber;

    @Column(name = "last_shot_taken")
    private Boolean lastShotTaken;

    public Boolean getLastShotTaken() {
        return lastShotTaken;
    }

    public void setLastShotTaken(Boolean lastShotTaken) {
        this.lastShotTaken = lastShotTaken;
    }

    public Integer getLastShotNumber() {
        return lastShotNumber;
    }

    public void setLastShotNumber(Integer lastShotNumber) {
        this.lastShotNumber = lastShotNumber;
    }

    public Timestamp getLastShotDate() {
        return lastShotDate;
    }

    public void setLastShotDate(Timestamp lastShotDate) {
        this.lastShotDate = lastShotDate;
    }

    public List<VaccinationShot> getVaccinationShots() {
        return vaccinationShots;
    }

    public void setVaccinationShots(List<VaccinationShot> vaccinationShots) {
        this.vaccinationShots = vaccinationShots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}