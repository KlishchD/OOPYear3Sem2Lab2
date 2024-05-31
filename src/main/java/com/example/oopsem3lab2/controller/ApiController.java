package com.example.oopsem3lab2.controller;

import com.example.oopsem3lab2.core.models.*;
import com.example.oopsem3lab2.repositories.TripCustomersRepository;
import com.example.oopsem3lab2.repositories.TripDescriptionRepository;
import com.example.oopsem3lab2.repositories.TripInstanceRepository;
import com.example.oopsem3lab2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@ControllerAdvice
public class ApiController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripInstanceRepository tripInstanceRepository;

    @Autowired
    private TripDescriptionRepository tripDescriptionRepository;

    @Autowired
    private TripCustomersRepository tripCustomersRepository;

    @PostMapping("/api/addTrip")
    @ResponseBody
    public void addTripDescription(Model model, @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam float baseCost,
                                   @RequestParam boolean isHot,
                                   @RequestParam float salesCost,
                                   @RequestParam int capacity) {
        TripDescription trip = new TripDescription();

        trip.setTitle(title);
        trip.setDescription(description);
        trip.setBaseCost(baseCost);
        trip.setHot(isHot);
        trip.setSalesCost(salesCost);
        trip.setCapacity(capacity);

        tripDescriptionRepository.save(trip);
    }

    @PostMapping("/api/addTripInstance")
    @ResponseBody
    public void addTripInstance(Model model, @RequestParam int id, @RequestParam LocalDate time) {
        TripInstance trip = new TripInstance();
        trip.setTripId(id);
        trip.setTime(time);

        tripInstanceRepository.save(trip);
    }

    @PostMapping("/api/buyTrip")
    @ResponseBody
    public void buyTrip(Model model, @RequestParam String username, @RequestParam int id) {
        TripCustomer customer = tripCustomersRepository.findByIdAndUsername(id, username);

        if (customer == null) {
            customer = new TripCustomer();
            customer.setUsername(username);
            customer.setId(id);
        } else {
            tripCustomersRepository.delete(customer);
        }

        customer.setStatus(TripPaymentStatus.PaidInFull);

        tripCustomersRepository.save(customer);
    }

    @PostMapping("/api/refundTrip")
    @ResponseBody
    public void refundTrip(Model model, @RequestParam String username, @RequestParam int id) {
        TripCustomer customer = tripCustomersRepository.findByIdAndUsername(id, username);

        if (customer != null) {
            tripCustomersRepository.delete(customer);
        }
    }

    @GetMapping("/api/findTripByPrompt")
    @ResponseBody
    public List<TripDescription> findTripByPrompt(Model model, @RequestParam String prompt) {
        return tripDescriptionRepository.findAllByPrompt(prompt);
    }

    @GetMapping("/api/findTripInstanceByID")
    @ResponseBody
    public TripInstance findTripInstanceByID(Model model, @RequestParam int id) {
        Optional<TripInstance> optionalBookInstance = tripInstanceRepository.findById(id);

        if (optionalBookInstance.isEmpty()) {
            return null;
        }

        TripInstance instance = optionalBookInstance.get();

        Optional<TripDescription> trip = tripDescriptionRepository.findById(instance.getTripId());
        instance.setTrip(trip.get());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(username).get();
        instance.setBuyer(user);

        TripCustomer customer = tripCustomersRepository.findByIdAndUsername(id, username);
        if (customer != null) {
            instance.setBuyer(user);
            instance.setStatus(customer.getStatus());
        }

        return instance;
    }

    @GetMapping("/api/findTripInstancesByPrompt")
    @ResponseBody
    public List<TripInstance> findTripInstancesByPrompt(Model model, @RequestParam String prompt) {
        List<TripDescription> descriptions = tripDescriptionRepository.findAllByPrompt(prompt);

        List<Integer> ids = descriptions.stream().map(x -> x.getId()).toList();
        List<TripInstance> instances = tripInstanceRepository.findAllByTripIds(ids);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(username).get();

        List<TripCustomer> customers = tripCustomersRepository.findAllByIdsAndUsername(ids, username);

        for (TripInstance instance : instances) {
            for (TripDescription description : descriptions) {
                if (instance.getTripId() == description.getId()) {
                    instance.setTrip(description);
                    break;
                }
            }
        }

        for (TripInstance instance : instances) {
            for (TripCustomer customer : customers) {
                if (instance.getId() == customer.getId()) {
                    instance.setStatus(customer.getStatus());
                    instance.setBuyer(user);
                }
            }
        }

        return instances;
    }

}