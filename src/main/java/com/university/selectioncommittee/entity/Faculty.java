package com.university.selectioncommittee.entity;

public class Faculty extends AbstractEntity {
    private int passingScore;
    private int recruitmentPlan;
    FacultyName facultyName;

    public enum FacultyName {
        ECONOMIC,
        LAW,
        JOURNALISM
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public int getRecruitmentPlan() {
        return recruitmentPlan;
    }

    public void setRecruitmentPlan(int recruitmentPlan) {
        this.recruitmentPlan = recruitmentPlan;
    }

    public FacultyName getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(FacultyName facultyName) {
        this.facultyName = facultyName;
    }
}
