package com.kripa.flightreservation.service;

import com.kripa.flightreservation.dto.ReservationRequest;
import com.kripa.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);
}
