package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymLocationServiceImpl implements GymLocationService{
    @Autowired
    private GymLocationRepository gymLocationRepository;
    @Override
    public List<GymLocation> findAll() {
        return gymLocationRepository.findAll();
    }

    @Override
    public Optional<GymLocation> findById(String id) {
        return gymLocationRepository.findById(id);
    }
    @Override
    public GymLocation save(GymLocation gymLocation) {
        return gymLocationRepository.save(gymLocation);
    }

    @Override
    public void deleteById(String id) {
        gymLocationRepository.deleteById(id);
    }

    @Override
    public GymLocation findByName(String name) {
        return gymLocationRepository.findByName(name);
    }
}
