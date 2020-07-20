package com.kripa.flightcheckin.integration;

import com.kripa.flightcheckin.integration.dto.Reservation;
import com.kripa.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
		
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);
}
