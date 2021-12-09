package edu.sjsu.cmpe275.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserVaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


}
