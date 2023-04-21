package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentLogRepository extends MongoRepository<EquipmentLog, String> {
    List<EquipmentLog> findByMemberId(String memberId);
}
