package com.kripa.flightreservation.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kripa.flightreservation.dto.ReservationRequest;
import com.kripa.flightreservation.entities.Flight;
import com.kripa.flightreservation.entities.Passenger;
import com.kripa.flightreservation.entities.Reservation;
import com.kripa.flightreservation.repos.FlightRepository;
import com.kripa.flightreservation.repos.PassengerRepository;
import com.kripa.flightreservation.repos.ReservationRepository;
import com.kripa.flightreservation.util.EmailUtil;
import com.kripa.flightreservation.util.PDFGenerator;

@Service 
public class ReservationServiceImpl implements ReservationService{
	
	@Value("${com.kripa.flightreservation.itinerary.dirpath}")
	private String ITERNARY_DIR ;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger("ReservationServiceImpl.class");
	
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
	
		LOGGER.info("Inside bookFlight()");
	Long flightId = request.getFlightId();
	
	LOGGER.info("fetching flight for flight id "+flightId);
	Flight flight = flightRepository.findById(flightId).get();
	
	Passenger passenger = new Passenger();
	passenger.setFirstName(request.getPassengerFirstName());
	passenger.setLastName(request.getPassengerLastName());
	passenger.setEmail(request.getPassengerEmail());
	LOGGER.info("Saving the passenger: "+passenger);
	Passenger savedPassenger = passengerRepository.save(passenger);
	
	Reservation reservation = new Reservation();
	reservation.setFlight(flight);
	reservation.setPassenger(savedPassenger);
	reservation.setCheckedIn(false);
	LOGGER.info("Saving the Reservation: " + reservation);
	Reservation savedReservation = reservationRepository.save(reservation);
	
			String filePath = ITERNARY_DIR+savedReservation.getId()+".pdf";
			LOGGER.info("Generating the Iternary");
			pdfGenerator.generateItinerary(savedReservation, filePath);
			LOGGER.info("Emaiing the Iternary");
			emailUtil.sendItenary(passenger.getEmail(), filePath);
		
	
	return savedReservation;
	}

}
