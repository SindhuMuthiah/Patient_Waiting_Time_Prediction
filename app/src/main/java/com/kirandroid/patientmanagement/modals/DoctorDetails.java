package com.kirandroid.patientmanagement.modals;

public class DoctorDetails {
    public String name;
    public String qualification;
    public String gender;
    public String age;
    public String address;
    public String phone;
    public String dept;
    public String url;

    public DoctorDetails() {
    }

    public DoctorDetails(String name, String qualification, String gender, String age, String address, String phone, String dept,String url) {
        this.name = name;
        this.qualification = qualification;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.dept = dept;
        this.url=url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
