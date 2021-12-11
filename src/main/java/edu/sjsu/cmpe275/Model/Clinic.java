package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "clinic")

public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clinic_name", nullable = false, unique = true)
    private String clinicName;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "open", nullable = false)
    private Integer open;

    @Column(name = "close", nullable = false)
    private Integer close;

    @Column(name = "business_hours", nullable = false)
    private Integer businessHours;

    @Column(name = "num_physicians", nullable = false)
    private Integer numPhysicians;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Integer getNumPhysicians() {
        return numPhysicians;
    }

    public void setNumPhysicians(Integer numPhysicians) {
        this.numPhysicians = numPhysicians;
    }

    public Integer getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(Integer businessHours) {
        this.businessHours = businessHours;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}