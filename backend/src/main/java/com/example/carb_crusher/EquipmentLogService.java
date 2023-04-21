package com.example.carb_crusher;

import java.util.List;

public interface EquipmentLogService {
    EquipmentLog save(EquipmentLog equipmentLog);
    EquipmentLog findById(String id);
    List<EquipmentLog> findAll();
    List<EquipmentLog> findByMemberId(String memberId);
    void deleteById(String id);
}
