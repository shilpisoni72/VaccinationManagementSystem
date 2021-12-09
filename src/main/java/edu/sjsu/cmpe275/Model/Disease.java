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
@Table(name = "disease")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "diseaseName", unique = true)
    private String diseaseName;

    @Column(name = "descritpion")
    private String description;

    public Disease(String diseaseName, String description) {
        this.diseaseName = diseaseName;
        this.description = description;
    }

    public Disease(){}
}
