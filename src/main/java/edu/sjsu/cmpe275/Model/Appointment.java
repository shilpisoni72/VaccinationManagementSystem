package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import java.util.Date;

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date" )
    private Date date;

    @Column(name = "checkIn" )
    private boolean checkIn;


    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToMany()
    @JoinColumn(name = "clinicId", referencedColumnName = "id")
    private Clinic clinic;

    @OneToMany()
    @JoinColumn(name = "vaccineId", referencedColumnName = "id")
    private Vaccine vaccine;



}
