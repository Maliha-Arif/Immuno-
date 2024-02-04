package sample;

import java.sql.*;
import java.util.Date;

public class UserData {
    int appointmentID;
    Date appointmentDate;
    Time appointmentTime;
    String appointmentHospital;
    String user;

    public UserData(int appointmentID, Date appointmentDate, Time appointmentTime, String appointmentHospital, String user) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentHospital = appointmentHospital;
        this.user = user;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentHospital() {
        return appointmentHospital;
    }

    public void setAppointmentHospital(String appointmentHospital) {
        this.appointmentHospital = appointmentHospital;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
