package org.unibl.etf.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "appointments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointments {
	
    @XmlElement(name = "appointment")
    private List<Appointment> appointments = new ArrayList<>();

    public Appointments() {
    	super();
    }

    public Appointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
