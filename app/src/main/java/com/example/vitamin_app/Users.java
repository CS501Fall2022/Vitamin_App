package com.example.vitamin_app;

public class Users {
    String user_email;
    String username;
    String age;
    String gender;

    String problem;
    String supplement1;
    String supplement2;
    String supplement3;
    String supplement4;
    String supplement5;

    public Users() {
    }

    public Users(String user_email, String username) {
        this.user_email = user_email;
        this.username = username;
    }

    public String getUserEmail() {
        return user_email;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSupplement1() {
        return supplement1;
    }

    public void setSupplement1(String supplement1) {
        this.supplement1 = supplement1;
    }

    public String getSupplement2() {
        return supplement2;
    }

    public void setSupplement2(String supplement2) {
        this.supplement2 = supplement2;
    }

    public String getSupplement3() {
        return supplement3;
    }

    public void setSupplement3(String supplement3) {
        this.supplement3 = supplement3;
    }

    public String getSupplement4() {
        return supplement4;
    }

    public void setSupplement4(String supplement4) {
        this.supplement4 = supplement4;
    }

    public String getSupplement5() {
        return supplement5;
    }

    public void setSupplement5(String supplement5) {
        this.supplement5 = supplement5;
    }
}
