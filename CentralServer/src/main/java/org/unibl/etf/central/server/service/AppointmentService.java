package org.unibl.etf.central.server.service;

import java.time.*;
import java.util.Comparator;
import java.util.List;
import org.unibl.etf.dto.*;
import org.unibl.etf.central.server.repository.*;
import org.unibl.etf.model.*;


public class AppointmentService {
	private final AppointmentRepository appointmentRepository;
	
	public AppointmentService() {
		appointmentRepository = AppointmentRepository.getInstance();
	}
	
	public List<Appointment> getAllAppointments() {
	    return appointmentRepository.getAppointments();
	}
	 
	public List<Appointment> getAllClientAppointments(int clientId) {
	    return appointmentRepository.getAppointments().stream()
	                .filter(a -> a.getClinetId() == clientId)
	                .toList();
	}
	 
	public List<Appointment> getClientHistoryAppointments(int clientId) {
		 LocalDate today = LocalDate.now();
		 return appointmentRepository.getAppointments().stream()
		          	.filter(a -> a.getClinetId() == clientId)
		          	.filter(a -> a.getDate().isBefore(today))
		            .sorted(Comparator.comparing(Appointment::getDate).reversed())
		            .toList();
	}

	public Appointment findById(int id) {
		return appointmentRepository.getAppointments()
	                .stream()
	                .filter(a -> a.getId() == id)
	                .findFirst()
	                .orElse(null);
	}
	 
	private Appointment findOverlappingAppointment(LocalDate date, LocalTime time) {
		return appointmentRepository.getAppointments()
		            .stream()
		            .filter(a -> a.getDate().equals(date)) // same day
		            .filter(a -> a.getStatus() == AppointmentStatus.SCHEDULED) // only look at active appointments
		            .filter(a -> {
		                LocalTime start = a.getTime();
		                LocalTime end = start.plusHours(1);
		                LocalTime requestedStart = time;
		                LocalTime requestedEnd = time.plusHours(1);

		                // checking for overlapping term intervals
		                return !requestedEnd.isBefore(start) && !requestedStart.isAfter(end);
		            })
		            .findFirst()
		            .orElse(null);
	}

	
	public boolean bookAppointment(AppointmentDTO appointmentDto) {
		Appointment appointment = new Appointment();
		appointment.setDate(appointmentDto.getDate());
		appointment.setTime(appointmentDto.getTime());
		appointment.setServiceType(appointmentDto.getServiceType());
		appointment.setClinetId(appointmentDto.getClientId());
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		
		Appointment overlapping = findOverlappingAppointment(appointmentDto.getDate(), appointmentDto.getTime());
		// termin slobodan
		if(overlapping == null) {
			createNewAppointment(appointment);
			return true;
		}
		return false;
	}
	
	
	public boolean createNewAppointment(Appointment appointment) {
		if(appointment != null)
			return addAppointment(appointment);
		return false;
	}
	
	public Appointment removeAppointment(int id) {
		Appointment appointment = findById(id);
		if(appointment != null) {
			deleteAppointment(id);
			return appointment;
		}
		return null;
	}
	
	public boolean updateAppointment(int id, Appointment updatedAppointment) {
    	Appointment existing = findById(id);
        if (existing == null) 
        	return false;

        int index = appointmentRepository.getAppointments().indexOf(existing);
        appointmentRepository.getAppointments().set(index, updatedAppointment);
        appointmentRepository.save();
        return true;
	}
	 
	public boolean completeAppointment(int id) {
		 Appointment appointment = findById(id);
		 if(appointment != null) {
			 appointment.setStatus(AppointmentStatus.COMPLETED);
			 return updateAppointment(id, appointment);
		 }
		 return false;
	}

	public boolean cancelAppointment(int id){
		 Appointment appointment = findById(id);
		 if(appointment != null) {
			 appointment.setStatus(AppointmentStatus.CANCELLED);
			 return updateAppointment(id, appointment);
		 }
		 return false;
	}
	
	private boolean addAppointment(Appointment appointment) {
        boolean result = appointmentRepository.getAppointments().add(appointment);
        appointmentRepository.save();
        return result;
    }

    private boolean deleteAppointment(int id) {
    	boolean result = appointmentRepository.getAppointments().removeIf(a -> a.getId() == id);
    	appointmentRepository.save();
        return result;
    }
}	
