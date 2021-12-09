package edu.sjsu.cmpe275.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "user")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "middleName" )
    private String middleName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "dob", nullable = false)
    private int dob;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone")
    private String phone;

    @OneToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    //primary key of user table, referencedColumn
    //name is the foriegn key name in Appointment
    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments =new ArrayList<>();

}
