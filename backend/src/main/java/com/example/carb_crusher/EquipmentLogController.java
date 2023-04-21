package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/equipment_logs")

public class EquipmentLogController {

    @Autowired
    private EquipmentLogService equipmentLogService;

    @PostMapping
    public EquipmentLog save(@RequestBody EquipmentLog equipmentLog) {
        return equipmentLogService.save(equipmentLog);
    }

    @GetMapping("/{id}")
    public EquipmentLog findById(@PathVariable String id) {
        return equipmentLogService.findById(id);
    }

    @GetMapping
    public List<EquipmentLog> findAll() {
        return equipmentLogService.findAll();
    }

    @GetMapping("/member/{memberId}")
    public List<EquipmentLog> findByMemberId(@PathVariable String memberId) {
        return equipmentLogService.findByMemberId(memberId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        equipmentLogService.deleteById(id);
    }
}
