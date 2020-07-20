package com.kripa.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kripa.flightcheckin.integration.ReservationRestClient;
import com.kripa.flightcheckin.integration.dto.Reservation;
import com.kripa.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {
	
	@Autowired
	ReservationRestClient restClient;
	
	@RequestMapping("/showStartCheckIn")
	public String showStartCheckin() {
		return "startCheckIn";
		
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelmap) {
		Reservation reservation = restClient.findReservation(reservationId);
		modelmap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
		
	}
	
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId, @RequestParam("numberOfBags")int numberOfBags) {
		ReservationUpdateRequest reservationRequest = new ReservationUpdateRequest();
		reservationRequest.setId(reservationId);
		reservationRequest.setCheckedIn(true);
		reservationRequest.setNumberOfBags(numberOfBags);
		restClient.updateReservation(reservationRequest);
		return "checkInConfirmation";
	}
}
