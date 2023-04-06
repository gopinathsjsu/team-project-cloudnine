package com.example.carb_crusher;

import java.util.List;

public interface CheckInService {
    List<CheckInStats> getCheckInStats(String period, String locationId);
}
