package edu.sjsu.cmpe275.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@XmlRootElement
@Entity
@Table(name = "vaccine")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Vaccine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique=true)
    private String name;
    
    
//    @ManyToMany(mappedBy = "", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"","",""})
//    private List<Diseases> diseases;


    @Column(name = "manufacturer",nullable=false)
    private String manufacturer;

    @Column(name = "numberOfShots", columnDefinition = "integer default 1", nullable=false)
    private int numberOfShots;

    @Column(name = "shotstaken", columnDefinition = "integer default 0", nullable=false)
    private int shotstaken;
    
    @Column(name = "shotInternalVal")
    private int shotInternalVal;

    @Column(name = "duration", columnDefinition = "integer default 0", nullable=false) // here value 0 corresponds to lifetime
    private int duration;


}
