package com.example.airline_api.controllers;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    // Display specific passenger details
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassengerById(@PathVariable Long id){
        return new ResponseEntity(passengerService.findPassenger(id), HttpStatus.OK);
    }

    // Add a new passenger
    @PostMapping
    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger){
        passengerService.savePassenger(passenger);
        return new ResponseEntity(passengerService.findAllPassengers(), HttpStatus.CREATED);
    }

    // Delete passenger
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deletePassenger(@PathVariable Long id){
        passengerService.removePassengersFromFlights(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    // update
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger, @PathVariable Long id){
        passengerService.updatePassenger(passenger, id);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

}
