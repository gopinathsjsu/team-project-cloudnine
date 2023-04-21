package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends MongoRepository<Membership, String> {
}
