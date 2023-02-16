package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public Flight updateFlight(Flight flight, Long id){
        Flight flightToUpdate = flightRepository.findById(id).get();
        flightToUpdate.setDestination(flight.getDestination());
        flightToUpdate.setCapacity(flight.getCapacity());
        flightToUpdate.setPassengers(flight.getPassengers());

        flightRepository.save(flightToUpdate);
        return flightToUpdate;
    }

    public void saveFlight(Flight flight){
        flightRepository.save(flight);
    }

    public Flight addPassengerToFlight(long flightId, Passenger passenger) throws IllegalStateException{
        Flight flight = flightRepository.findById(flightId).get();
        List<Passenger> passengers = flight.getPassengers();
        if (passengers.size() >= flight.getCapacity()){
            throw new IllegalStateException("Flight is at full capacity");
        }

        passengers.add(passenger);
        flight.setPassengers(passengers);
        flightRepository.save(flight);
        return flight;
    }

    public Flight findFlight(Long id){return flightRepository.findById(id).get();}

    public List<Flight> findAllFlights(){return flightRepository.findAll();}

    public List<Flight> findAllFlightsUnderFullCapacity(int capacity){
        return flightRepository.findByCapacityLessThan(capacity);
    }

    public void deleteFlight(Long id){ flightRepository.deleteById(id);}

    // get flights by destination
    public List<Flight> getFlightsByDestination(String destination) {
        List<Flight> flights = flightRepository.findAllByDestination(destination);
        return flights;
    }

}
