package com.example.loginpage;

public class FacultyDto {
    private String facultyName;
    private String facultyRollNo;
    private String facultyPhoneNo;
    private String facultyDesignation;
    private String facultySubjectOfInterest;
    private String facultyExperience;
    private String facultyAddress;
    private String facultyEducation;
    private String facultyGender;
    private String facultyPassword;

    public FacultyDto() {
    }

    public FacultyDto(String facultyName, String facultyRollNo, String facultyPhoneNo, String facultyDesignation, String facultySubjectOfInterest, String facultyExperience, String facultyAddress, String facultyEducation, String facultyGender, String facultyPassword) {
        this.facultyName = facultyName;
        this.facultyRollNo = facultyRollNo;
        this.facultyPhoneNo = facultyPhoneNo;
        this.facultyDesignation = facultyDesignation;
        this.facultySubjectOfInterest = facultySubjectOfInterest;
        this.facultyExperience = facultyExperience;
        this.facultyAddress = facultyAddress;
        this.facultyEducation = facultyEducation;
        this.facultyGender = facultyGender;
        this.facultyPassword = facultyPassword;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyRollNo() {
        return facultyRollNo;
    }

    public void setFacultyRollNo(String facultyRollNo) {
        this.facultyRollNo = facultyRollNo;
    }

    public String getFacultyPhoneNo() {
        return facultyPhoneNo;
    }

    public void setFacultyPhoneNo(String facultyPhoneNo) {
        this.facultyPhoneNo = facultyPhoneNo;
    }

    public String getFacultyDesignation() {
        return facultyDesignation;
    }

    public void setFacultyDesignation(String facultyDesignation) {
        this.facultyDesignation = facultyDesignation;
    }

    public String getFacultySubjectOfInterest() {
        return facultySubjectOfInterest;
    }

    public void setFacultySubjectOfInterest(String facultySubjectOfInterest) {
        this.facultySubjectOfInterest = facultySubjectOfInterest;
    }

    public String getFacultyExperience() {
        return facultyExperience;
    }

    public void setFacultyExperience(String facultyExperience) {
        this.facultyExperience = facultyExperience;
    }

    public String getFacultyAddress() {
        return facultyAddress;
    }

    public void setFacultyAddress(String facultyAddress) {
        this.facultyAddress = facultyAddress;
    }

    public String getFacultyEducation() {
        return facultyEducation;
    }

    public void setFacultyEducation(String facultyEducation) {
        this.facultyEducation = facultyEducation;
    }

    public String getFacultyGender() {
        return facultyGender;
    }

    public void setFacultyGender(String facultyGender) {
        this.facultyGender = facultyGender;
    }

    public String getFacultyPassword() {
        return facultyPassword;
    }

    public void setFacultyPassword(String facultyPassword) {
        this.facultyPassword = facultyPassword;
    }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "facultyName='" + facultyName + '\'' +
                ", facultyRollNo='" + facultyRollNo + '\'' +
                ", facultyPhoneNo='" + facultyPhoneNo + '\'' +
                ", facultyDesignation='" + facultyDesignation + '\'' +
                ", facultySubjectOfInterest='" + facultySubjectOfInterest + '\'' +
                ", facultyExperience='" + facultyExperience + '\'' +
                ", facultyAddress='" + facultyAddress + '\'' +
                ", facultyEducation='" + facultyEducation + '\'' +
                ", facultyGender='" + facultyGender + '\'' +
                ", facultyPassword='" + facultyPassword + '\'' +
                '}';
    }
}
