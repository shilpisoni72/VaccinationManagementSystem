package edu.sjsu.cmpe275.Helper.Model;

import edu.sjsu.cmpe275.Model.Appointment;

import java.util.Date;

public class VaccinationDue {
    private String vaccinatioName;
    private int numberOfShotDue;
    private Date dueDate;
    private Appointment appointment;
    private String status;

    public VaccinationDue(String vaccinatioName, int numberOfShotsDue, Date dueDate, Appointment appointment, String status) {
        this.vaccinatioName = vaccinatioName;
        this.numberOfShotDue = numberOfShotsDue;
        this.dueDate = dueDate;
        this.appointment = appointment;
        this.status = status;
    }

    public VaccinationDue() {
    }

    public String getVaccinatioName() {
        return vaccinatioName;
    }

    public void setVaccinatioName(String vaccinatioName) {
        this.vaccinatioName = vaccinatioName;
    }

    public int getNumberOfShotDue() {
        return numberOfShotDue;
    }

    public void setNumberOfShotDue(int numberOfShotDue) {
        this.numberOfShotDue = numberOfShotDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
