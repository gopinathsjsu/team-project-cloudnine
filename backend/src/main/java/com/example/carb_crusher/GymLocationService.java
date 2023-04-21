package com.example.carb_crusher;

import java.util.List;
import java.util.Optional;

public interface GymLocationService {
    List<GymLocation> findAll();
    Optional<GymLocation> findById(String id);
    GymLocation save(GymLocation gymLocation);
    void deleteById(String id);
    GymLocation findByName(String name);
}
