package com.example.loginpage;

public class StudentsDto {

    private String studentName;
    private String studentRollNo;
    private String studentPhoneNo;
    private String studentCurrentYear;
    private String studentGmail;
    private String studentAddress;
    private String studentGender;
    private String studentPassword;

    public StudentsDto() {
    }

    public StudentsDto(String studentName, String studentRollNo, String studentPhoneNo, String studentCurrentYear, String studentGmail, String studentAddress, String studentGender, String studentPassword) {
        this.studentName = studentName;
        this.studentRollNo = studentRollNo;
        this.studentPhoneNo = studentPhoneNo;
        this.studentCurrentYear = studentCurrentYear;
        this.studentGmail = studentGmail;
        this.studentAddress = studentAddress;
        this.studentGender = studentGender;
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getStudentPhoneNo() {
        return studentPhoneNo;
    }

    public void setStudentPhoneNo(String studentPhoneNo) {
        this.studentPhoneNo = studentPhoneNo;
    }

    public String getStudentCurrentYear() {
        return studentCurrentYear;
    }

    public void setStudentCurrentYear(String studentCurrentYear) {
        this.studentCurrentYear = studentCurrentYear;
    }

    public String getStudentGmail() {
        return studentGmail;
    }

    public void setStudentGmail(String studentGmail) {
        this.studentGmail = studentGmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    @Override
    public String toString() {
        return "StudentsDto{" +
                "studentName='" + studentName + '\'' +
                ", studentRollNo='" + studentRollNo + '\'' +
                ", studentPhoneNo='" + studentPhoneNo + '\'' +
                ", studentCurrentYear='" + studentCurrentYear + '\'' +
                ", studentGmail='" + studentGmail + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                '}';
    }
}
