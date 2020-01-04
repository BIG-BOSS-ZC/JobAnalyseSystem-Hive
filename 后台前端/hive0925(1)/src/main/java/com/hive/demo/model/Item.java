package com.hive.demo.model;

public class Item {
    private String jobname;
    private Double minsalary;
    private Double maxsalary;
    private String exprience;
    private String edu;
    private String company;
    private String trade;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Item(String jobname, Double minsalary, Double maxsalary, String exprience, String edu, String company, String trade) {
        this.jobname = jobname;
        this.minsalary = minsalary;
        this.maxsalary = maxsalary;
        this.exprience = exprience;
        this.edu = edu;
        this.company=company;
        this.trade = trade;
    }

    public Item() {
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public Double getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(Double minsalary) {
        this.minsalary = minsalary;
    }

    public Double getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(Double maxsalary) {
        this.maxsalary = maxsalary;
    }

    public String getExprience() {
        return exprience;
    }

    public void setExprience(String exprience) {
        this.exprience = exprience;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }
}
