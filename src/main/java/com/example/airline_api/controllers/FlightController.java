package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlightsAndFilters(
            @RequestParam(required = false, name = "capacity") Integer capacity
    ){
//      GET /flights?capacity=200
        if (capacity != null){
            return new ResponseEntity<>(flightService.findAllFlightsUnderFullCapacity(capacity), HttpStatus.OK);
        }
//      GET /flights
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable Long id){
        Flight foundFlight = flightService.findFlight(id);
        return new ResponseEntity(foundFlight, HttpStatus.OK);
    }

    // filter flights by destination
    @GetMapping(value = "/{destination}")
    public ResponseEntity<List<Flight>> getFlightsByDestination(@PathVariable String destination) {
        List<Flight> flights = flightService.getFlightsByDestination(destination);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Add details of a new flight - with or without passengers
    @PostMapping
    public ResponseEntity<List<Flight>> addNewFlight(@RequestBody Flight flight){
        flightService.saveFlight(flight);
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@RequestBody Passenger passenger, @PathVariable Long flightId){
        try{
            Flight flight = flightService.addPassengerToFlight(flightId, passenger);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
