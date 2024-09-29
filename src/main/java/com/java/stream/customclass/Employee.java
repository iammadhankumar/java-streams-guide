package com.java.stream.customclass;

import java.util.List;

import lombok.Data;

@Data
class Employee {
    private String name;
    private int id;
    private int salary;
    private String department;
    private String jobTitle;
    private int yearsOfExperience;
    private String employmentType;
    private List<String> skills;

    // Constructor
    public Employee(String name, int id, int salary, String department, String jobTitle, int yearsOfExperience, String employmentType, List<String> skills) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.department = department;
        this.jobTitle = jobTitle;
        this.yearsOfExperience = yearsOfExperience;
        this.employmentType = employmentType;
        this.skills = skills;
    }


    @Override
    public String toString() {
        return name + " (ID: " + id + "), " + jobTitle + " in " + department + ", Salary: " + salary;
    }
}