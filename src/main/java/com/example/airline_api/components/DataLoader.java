package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Passengers
        Passenger passenger1 = new Passenger("Aya", "aya@bnta.com");
        passengerRepository.save(passenger1);

        Passenger passenger2 = new Passenger("Samra", "samra@bnta.com");
        passengerRepository.save(passenger2);

        Passenger passenger3 = new Passenger("Chinika", "chinika@bnta.com");
        passengerRepository.save(passenger3);

        Passenger passenger4 = new Passenger("Ryder", "ryder@bnta.com");
        passengerRepository.save(passenger4);

        Passenger passenger5 = new Passenger("Wilson", "wilson@bnta.com");
        passengerRepository.save(passenger5);

        Passenger passenger6 = new Passenger("Diana", "diana@bnta.com");
        passengerRepository.save(passenger6);


        //Flights
        Flight flight1 = new Flight("London", 200, "17-02-2023", "14:30");
        flight1.addPassenger(passenger1);
        flightRepository.save(flight1);

        Flight flight2 = new Flight("Tokyo", 200, "23-03-2023", "23:30");
        flight2.addPassenger(passenger1);
        flightRepository.save(flight2);

        Flight flight3 = new Flight("Seoul", 200, "07-05-2023", "06:00");
        flight3.addPassenger(passenger2);
        flightRepository.save(flight3);

        Flight flight4 = new Flight("Bangkok", 200, "29-10-2023", "13:00");
        flight4.addPassenger(passenger2);
        flightRepository.save(flight4);

        Flight flight5 = new Flight("Berlin", 200, "08-04-2023", "02:30");
        flight5.addPassenger(passenger3);
        flightRepository.save(flight5);

        Flight flight6 = new Flight("Barcelona", 200, "24-03-2023", "17:10");
        flight6.addPassenger(passenger3);
        flightRepository.save(flight6);

        Flight flight7 = new Flight("Edinburgh", 200, "16-04-2023", "04:50");
        flight7.addPassenger(passenger4);
        flightRepository.save(flight7);

        Flight flight8 = new Flight("Dubai", 200, "26-02-2023", "18:30");
        flight8.addPassenger(passenger4);
        flightRepository.save(flight8);

        Flight flight9 = new Flight("Chicago", 200, "17-02-2023", "19:30");
        flight9.addPassenger(passenger5);
        flightRepository.save(flight9);

        Flight flight10 = new Flight("Amsterdam", 200, "17-02-2023", "12:30");
        flight10.addPassenger(passenger5);
        flightRepository.save(flight10);

        Flight flight11 = new Flight("Prague", 200, "12-03-2023", "21:00");
        flight11.addPassenger(passenger6);
        flightRepository.save(flight11);

        Flight flight12 = new Flight("Marrakech", 200, "25-05-2023", "22:30");
        flight12.addPassenger(passenger6);
        flightRepository.save(flight12);







    }
}
