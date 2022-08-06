package com.kirandroid.patientmanagement.modals;

public class PatientDetails {
    public String name;
    public String pid;
    public String gender;
    public String age;
    public String address;
    public String phone;
    public String prob;

    public PatientDetails() {
    }

    public PatientDetails(String name, String pid, String gender, String age, String address, String phone, String prob) {
        this.name = name;
        this.pid = pid;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.prob = prob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProb() {
        return prob;
    }

    public void setProb(String prob) {
        this.prob = prob;
    }
}
