package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "equipment_logs")
public class EquipmentLog {
    @Id
    private String id;
    private String memberId;
    private String equipmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EquipmentLog() {
    }

    public EquipmentLog(String memberId, String equipmentType, LocalDateTime startTime, LocalDateTime endTime) {
        this.memberId = memberId;
        this.equipmentType = equipmentType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
