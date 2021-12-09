package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import java.util.Date;

public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "clinicName", unique = true, nullable = false)
    private String clinicName;

    @OneToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
    
    @Column(name = "open", columnDefinition = "integer default 8")
    private int open;

    @Column(name = "close", columnDefinition = "integer default 17")
    private int close;

    @Column(name = "businessHours", columnDefinition = "integer default 8")
    private int businessHours;

    @Column(name = "numPhysicians")
    private int numPhysicians;

    @OneToMany(optional = false)
    @JoinColumn(name = "appointmentId", referencedColumnName = "id")
    private Appointment appointment;
}
