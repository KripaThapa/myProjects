package com.kripa.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kripa.flightreservation.dto.ReservationRequest;
import com.kripa.flightreservation.entities.Flight;
import com.kripa.flightreservation.entities.Reservation;
import com.kripa.flightreservation.repos.FlightRepository;
import com.kripa.flightreservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger("ReservationController.class");
	

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelmap) {
		LOGGER.info("Inside showCompleteReservation()");
		Flight flight = flightRepository.findById(flightId).get();
		modelmap.addAttribute("flight", flight);
		return "completeReservation";
	}

	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelmap) {
		LOGGER.info("Inside completeReservation()");
		Reservation reservation = reservationService.bookFlight(request);
		modelmap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
		return "reservationConfirmation";
	}
}
