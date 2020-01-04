package com.hive.demo.model;

public class CitySa {
    private String cityname;
    private Double salary;

    public CitySa(String cityname, Double salary) {
        this.cityname = cityname;
        this.salary = salary;
    }

    public CitySa() {
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
