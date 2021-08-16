package com.university.selectioncommittee.entity;

public class Applicant extends User{

    private int score;
    private String phone;


    public Applicant(){
        role = UserRole.APPLICANT;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (score != applicant.score) return false;
        return phone != null ? phone.equals(applicant.phone) : applicant.phone == null;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Applicant{" +
                "score=" + score +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}');
        return sb.toString();
    }
}
