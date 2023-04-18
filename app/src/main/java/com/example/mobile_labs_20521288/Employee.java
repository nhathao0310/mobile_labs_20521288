package com.example.mobile_labs_20521288;

import androidx.annotation.NonNull;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - $" + salary;
    }
}








