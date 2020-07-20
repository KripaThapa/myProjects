package com.kripa.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kripa.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
