package edu.sjsu.cmpe275.Model;

import javax.persistence.Column;
import java.util.Date;

public class Shot {
    @Column(name = "date")
    private Date date;

    @Column(name = "shotNumber", nullable = false)
    private int shotNumber;



}
