package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public List<Passenger> findAllPassengers(){return passengerRepository.findAll();}

    public Passenger findPassenger(Long id){return passengerRepository.findById(id).get();}

    public void savePassenger(Passenger passenger){passengerRepository.save(passenger);}

    public void removePassengersFromFlights(Long id){
        Passenger foundPassenger = passengerRepository.getById(id);
        for (Flight flight : foundPassenger.getFlights()){
            flight.removePassenger(foundPassenger);
        }
    }

    public void deletePassenger(Long id){passengerRepository.deleteById(id);}

    public void updatePassenger(Passenger passenger, Long id){
        Passenger passengerToUpdate = passengerRepository.findById(id).get();
        passengerToUpdate.setName(passenger.getName());
        passengerToUpdate.setEmail(passenger.getEmail());
        passengerRepository.save(passengerToUpdate);
    }

}
