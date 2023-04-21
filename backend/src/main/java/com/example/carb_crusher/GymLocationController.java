package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

@RequestMapping("/api/gym-locations")

public class GymLocationController {

    @Autowired
    private GymLocationService gymLocationService;


    @GetMapping
    public List<GymLocation> findAll() {
        return gymLocationService.findAll();
    }


    @GetMapping("/{id}")
    public Optional<GymLocation> findById(@PathVariable String id) {
        return gymLocationService.findById(id);
    }

    @PostMapping
    public GymLocation save(@RequestBody GymLocation gymLocation) {
        return gymLocationService.save(gymLocation);
    }

    @PutMapping("/{id}")
    public GymLocation update(@PathVariable String id, @RequestBody GymLocation updatedGymLocation) {
        Optional<GymLocation> gymLocationOptional = gymLocationService.findById(id);
        if (gymLocationOptional.isPresent()) {
            GymLocation gymLocation = gymLocationOptional.get();
            gymLocation.setName(updatedGymLocation.getName());
            gymLocation.setAddress(updatedGymLocation.getAddress());
            return gymLocationService.save(gymLocation);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        gymLocationService.deleteById(id);
    }

    @GetMapping("/name/{name}")
    public GymLocation findByName(@PathVariable String name) {
        return gymLocationService.findByName(name);
    }
}
