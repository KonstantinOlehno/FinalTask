package com.university.selectioncommittee.entity;

public class Admin extends User {

    public Admin(){
        role = UserRole.ADMIN;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin{" +
                "role=" + role +
                '}');
        return sb.toString();
    }
}
