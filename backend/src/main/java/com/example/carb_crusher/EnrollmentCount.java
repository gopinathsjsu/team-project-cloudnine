package com.example.carb_crusher;

public class EnrollmentCount {
    private String classScheduleId;
    private String className;
    private int enrollmentCount;

    public EnrollmentCount(String classScheduleId, String className, int enrollmentCount) {
        this.classScheduleId = classScheduleId;
        this.className = className;
        this.enrollmentCount = enrollmentCount;
    }

    public String getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(String classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }
}
