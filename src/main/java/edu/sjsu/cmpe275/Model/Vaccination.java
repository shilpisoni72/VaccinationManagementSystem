package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import java.util.List;

@Table(name = "vaccination")
@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vaccination_id", unique = true)
    private List<Disease> diseases;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "number_of_shots", nullable = false)
    private Integer numberOfShots;

    @Column(name = "shot_interval", nullable = false)
    private Integer shotInterval;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getShotInterval() {
        return shotInterval;
    }

    public void setShotInterval(Integer shotInterval) {
        this.shotInterval = shotInterval;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public void setNumberOfShots(Integer numberOfShots) {
        this.numberOfShots = numberOfShots;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}