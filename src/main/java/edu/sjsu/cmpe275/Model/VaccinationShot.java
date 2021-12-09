package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "vaccination_shot")
@Entity
public class VaccinationShot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "shot_number", nullable = false)
    private Integer shotNumber;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Integer getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(Integer shotNumber) {
        this.shotNumber = shotNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}