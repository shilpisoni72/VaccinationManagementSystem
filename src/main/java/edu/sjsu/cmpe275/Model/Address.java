package edu.sjsu.cmpe275.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "address")

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "addressLine1", nullable = false)
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @Column(name = "city", nullable = false )
    private String city;

    @Column(name = "state", nullable = false )
    private String state;

    @Column(name = "zipcode", nullable = false )
    private int zipcode;


}
