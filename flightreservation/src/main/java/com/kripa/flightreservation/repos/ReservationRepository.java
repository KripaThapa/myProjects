package com.kripa.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kripa.flightreservation.entities.Passenger;
import com.kripa.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
