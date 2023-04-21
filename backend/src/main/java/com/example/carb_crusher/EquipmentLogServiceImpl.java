package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentLogServiceImpl implements EquipmentLogService {

    @Autowired
    private EquipmentLogRepository equipmentLogRepository;

    @Override
    public EquipmentLog save(EquipmentLog equipmentLog) {
        return equipmentLogRepository.save(equipmentLog);
    }

    @Override
    public EquipmentLog findById(String id) {
        return equipmentLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<EquipmentLog> findAll() {
        return equipmentLogRepository.findAll();
    }

    @Override
    public List<EquipmentLog> findByMemberId(String memberId) {
        return equipmentLogRepository.findByMemberId(memberId);
    }

    @Override
    public void deleteById(String id) {
        equipmentLogRepository.deleteById(id);
    }
}
