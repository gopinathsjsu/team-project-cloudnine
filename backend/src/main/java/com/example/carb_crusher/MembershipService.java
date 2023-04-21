package com.example.carb_crusher;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    List<Membership> findAll();
    Optional<Membership> findById(String id);
    Membership save(Membership membership);
    void deleteById(String id);
}
