package com.example.carb_crusher;

import java.util.Date;

public class EnrollmentStats {
    private String period;
    private long enrollmentCount;

    public EnrollmentStats(String period, long enrollmentCount) {
        this.period = period;
        this.enrollmentCount = enrollmentCount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public long getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(long enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }
}