package edu.sjsu.cmpe275.Service;

import edu.sjsu.cmpe275.Helper.Model.VaccinationDue;
import edu.sjsu.cmpe275.Model.*;
import edu.sjsu.cmpe275.Repository.UserVaccinationRepository;
import edu.sjsu.cmpe275.Repository.VaccinationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VaccinationRecordServiceImpl implements VaccinationRecordService {

    @Autowired
    VaccinationRecordRepository vaccinationRecordRepository;

    @Override
    public List<VaccinationDue> getVaccinationsDue(Long userId, String date) {
        try {
            Date currentDate = new Date(date);
            Date endDate = getEndDate(date);
            List<VaccinationRecord> userVaccinationRecords = vaccinationRecordRepository.findAllByUserId(userId);
            Map<Long, List<VaccinationRecord>> groupedVaccinationRecords = getGroupedVaccinationRecords((userVaccinationRecords));
            List<VaccinationDue> vaccinationsDue = new ArrayList<VaccinationDue>();

            Comparator<VaccinationRecord> compareByShotNumber = (VaccinationRecord v1, VaccinationRecord v2) ->
                    v1.getShotNumber().compareTo(v2.getShotNumber());

            for (Map.Entry<Long, List<VaccinationRecord>> vaccinationRecords : groupedVaccinationRecords.entrySet()) {

                if (!vaccinationRecords.getValue().isEmpty()) {
                    Collections.sort(vaccinationRecords.getValue(), compareByShotNumber);
                    VaccinationDue vaccinationDue = new VaccinationDue();

                    int numberOfShotsPerVaccine = vaccinationRecords.getValue().get(0).getVaccination().getNumberOfShots();
                    int shotInterval = vaccinationRecords.getValue().get(0).getVaccination().getShotInterval();
                    int duration = vaccinationRecords.getValue().get(0).getVaccination().getDuration();

                    vaccinationDue.setVaccinatioName(vaccinationRecords.getValue().get(0).getVaccination().getName());
                    int latestShotIndex = 0;
                    int latestShotNumber = 0;
                    Date latestShotDate = null;
                    VaccinationRecord latestShot = null;
                    for (int i = 0; i < vaccinationRecords.getValue().size(); i++) {
                        if (currentDate.after(vaccinationRecords.getValue().get(i).getShotDate())) {
                            latestShotIndex = i;
                            latestShotNumber = vaccinationRecords.getValue().get(i).getShotNumber();
                            latestShotDate = vaccinationRecords.getValue().get(i).getShotDate();
                            latestShot = vaccinationRecords.getValue().get(i);
                        }
                    }
                    if (vaccinationRecords.getValue().size() % numberOfShotsPerVaccine != 0) {
                        if (latestShot.getTaken()) {
                            if (getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate(), shotInterval).before(endDate)) {
                                vaccinationDue.setStatus("DUE");
                                vaccinationDue.setDueDate(getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate(), shotInterval));
                                vaccinationDue.setNumberOfShotDue(vaccinationRecords.getValue().get(latestShotIndex).getShotNumber() + 1);
                                vaccinationDue.setVaccinationRecord(vaccinationRecords.getValue().get(latestShotIndex + 1));
                                if (latestShotIndex + 1 <= vaccinationRecords.getValue().size()) {// could write this and the inset if statement as one but too long
                                    if (vaccinationRecords.getValue().get(latestShotIndex + 1).getAppointment().getBookedOn().before(currentDate))
                                        vaccinationDue.setAppointment(vaccinationRecords.getValue().get(latestShotIndex + 1).getAppointment());
                                }
                                vaccinationsDue.add(vaccinationDue);
                            }
                        } else {
                            vaccinationDue.setStatus("OVERDUE");
                            vaccinationDue.setNumberOfShotDue(vaccinationRecords.getValue().get(latestShotIndex).getShotNumber());
                            vaccinationDue.setVaccinationRecord(vaccinationRecords.getValue().get(latestShotIndex));
                            vaccinationDue.setAppointment(vaccinationRecords.getValue().get(latestShotIndex).getAppointment());
                            if (latestShotIndex == 0)
                                vaccinationDue.setDueDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate());
                            else
                                vaccinationDue.setDueDate(getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex - 1).getAppointment().getDate(), shotInterval));
                            vaccinationsDue.add(vaccinationDue);
                        }
                    } else {
                        if (latestShot.getTaken()) {
                            if (getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate(), duration).before(endDate) && duration!=0){
                                vaccinationDue.setStatus("DUE");
                                vaccinationDue.setDueDate(getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate(), duration));
                                vaccinationDue.setNumberOfShotDue(vaccinationRecords.getValue().get(latestShotIndex).getShotNumber() + 1);
                                vaccinationDue.setVaccinationRecord(vaccinationRecords.getValue().get(latestShotIndex + 1));
                                if (latestShotIndex + 1 <= vaccinationRecords.getValue().size()) {// could write this and the inset if statement as one but too long
                                    if (vaccinationRecords.getValue().get(latestShotIndex + 1).getAppointment().getBookedOn().before(currentDate))
                                        vaccinationDue.setAppointment(vaccinationRecords.getValue().get(latestShotIndex + 1).getAppointment());
                                }
                                vaccinationsDue.add(vaccinationDue);
                            }
                        } else if(duration!=0) {
                            vaccinationDue.setStatus("OVERDUE");
                            vaccinationDue.setNumberOfShotDue(vaccinationRecords.getValue().get(latestShotIndex).getShotNumber());
                            vaccinationDue.setVaccinationRecord(vaccinationRecords.getValue().get(latestShotIndex));
                            vaccinationDue.setAppointment(vaccinationRecords.getValue().get(latestShotIndex).getAppointment());
                            if (latestShotIndex == 0)
                                vaccinationDue.setDueDate(vaccinationRecords.getValue().get(latestShotIndex).getAppointment().getDate());
                            else
                                vaccinationDue.setDueDate(getNextShotDate(vaccinationRecords.getValue().get(latestShotIndex - 1).getAppointment().getDate(), shotInterval));
                            vaccinationsDue.add(vaccinationDue);
                        }
                    }
                }
            }
            return vaccinationsDue;
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public List<VaccinationRecord> getVaccinationRecords(Long userId) {
        try {
            List<VaccinationRecord> userVaccinationHistory = new ArrayList<VaccinationRecord>();
            vaccinationRecordRepository.findAllByUserId(userId).forEach(userVaccinationHistory::add);
            return userVaccinationHistory;
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public List<VaccinationRecord> getVaccinationRecordsByVaccine(Long vaccinationId, Long userId) {
        try{
            List<VaccinationRecord> vaccinationHistory = new ArrayList<>();
            vaccinationRecordRepository.findAllByVaccinationIdAndUserId(vaccinationId, userId).forEach(vaccinationHistory::add);
            if (!vaccinationHistory.isEmpty())
                return vaccinationHistory;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    @Override
    public List<VaccinationRecord> getVaccinationRecordsByAppointment(Long appointmentId, Long userId) {
        try{
            List<VaccinationRecord> vaccinationHistory = new ArrayList<>();
            vaccinationRecordRepository.findAllByAppointmentIdAndUserId(appointmentId, userId).forEach(vaccinationHistory::add);
            if (!vaccinationHistory.isEmpty())
                return vaccinationHistory;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    public Map<Long, List<VaccinationRecord>> getGroupedVaccinationRecords(List<VaccinationRecord> vaccinationRecords) {
        Map<Long, List<VaccinationRecord>> groupedVaccinationRecords = new HashMap<Long, List<VaccinationRecord>>();
        try {
            for (VaccinationRecord vaccinationRecord :
                    vaccinationRecords) {
                groupedVaccinationRecords.computeIfAbsent(vaccinationRecord.getVaccination().getId(), k -> new ArrayList<>()).add(vaccinationRecord);
            }
            return groupedVaccinationRecords;
        } catch (Exception exception) {
            System.out.println(exception.getStackTrace());
        }
        return null;
    }

    public Date getEndDate(String date) {
        try {
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date currentDate = new Date(date);
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.MONTH, 12);
            return c.getTime();
        } catch (Exception exception) {
            System.out.println("");
        }
        return null;
    }

    public Date getNextShotDate(Date currentDate, int interval) {
        try {
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, interval);
            return c.getTime();
        } catch (Exception exception) {
            System.out.println("");
        }
        return null;
    }

}
